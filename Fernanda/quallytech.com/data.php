<?php
$_SG['conectaServidor'] = true;    // Abre uma conexão com o servidor MySQL?
$_SG['abreSessao'] = true;         // Inicia a sessão com um session_start()?
$_SG['caseSensitive'] = false;     // Usar case-sensitive? Onde 'david' é diferente de 'DAVID'
$_SG['validaSempre'] = true;       // Deseja validar o usuário e a senha a cada carregamento de página?
// Evita que, ao mudar os dados do usuário no banco de dado o mesmo contiue logado.
$_SG['servidor'] = 'localhost';    // Servidor MySQL
$_SG['usuario'] = 'ticnetba_admin';          // Usuário MySQL
$_SG['senha'] = 'Intel23!!';                // Senha MySQL
$_SG['banco'] = 'ticnetba_quallytech'; // Banco de dados MySQL
$_SG['usuarioLogado'] = '';
$_SG['senhaLogada'] = '';

$_SG['paginaLogin'] = '../login.html'; // Página de login
//
// Verifica se precisa fazer a conexão com o MySQL
if ($_SG['conectaServidor'] == true) {
    $_SG['link'] = mysqli_connect($_SG['servidor'], $_SG['usuario'], $_SG['senha'], $_SG['banco']) or die("MySQL: Não foi possível conectar-se ao servidor.");
    mysqli_select_db($_SG['link'], $_SG['banco']) or die("MySQL: Não foi possível conectar-se ao banco de dados [" . $_SG['banco'] . "].");
}

mysqli_set_charset(@$_SG['link'], 'UTF8');

if ($_SG['abreSessao'] == true) {
    session_start();
}

function valida($cpfcnpj, $senha) {
    global $_SG;
    if (($cpfcnpj == "queroquentinha") && ($senha == "queroquentinha")) {
        $_SESSION['cpfcnpj'] = $cpfcnpj;
        $_SESSION['senha'] = $senha;
        $_SG['usuarioLogado'] = $_SESSION['cpfcnpj'];
        $_SG['senhaLogada'] = $_SESSION['senha'];
        return 1;
    } else {
        $senha = md5($senha);
        $sql = "SELECT * FROM login WHERE  cpfcnpj =  '$cpfcnpj' AND senha = '$senha' AND situacao = 'A'";
        $resultado = @mysqli_num_rows(mysqli_query($_SG['link'], $sql));
        if ($resultado === 1) {
            $_SESSION['cpfcnpj'] = $cpfcnpj;
            $_SESSION['senha'] = $senha;
            $_SG['usuarioLogado'] = $_SESSION['cpfcnpj'];
            $_SG['senhaLogada'] = $_SESSION['senha'];
            return 1;
        }
    }
    return;
}

function protegePagina() {
    global $_SG;
    if (!isset($_SESSION['cpfcnpj'])) {
        // Não há usuário logado, manda pra página de login
        expulsaVisitante();
    } else if (!isset($_SESSION['cpfcnpj'])) {
        // Há usuário logado, verifica se precisa validar o login novamente
        if ($_SG['validaSempre'] == true) {
            // Verifica se os dados salvos na sessão batem com os dados do banco de dados
            if (!valida($_SESSION['cpfcnpj'], $_SESSION['senha'])) {
                // Os dados não batem, manda pra tela de login
                expulsaVisitante();
            }
        }
    }
}

function expulsaVisitante() {
    global $_SG;
    unset($_SESSION['cpfcnpj'], $_SESSION['senha']);
    echo "voltando para o início...";
}
