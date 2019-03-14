<?php
include("data.php");

$os = $_GET['os'];
$situacao = $_GET['situacao'];
$descricao = $_GET['descricao'];

$busca = @mysqli_query($_SG['link'], "SELECT * FROM ordem_servico WHERE os = '$os'");
$query1 = @mysqli_num_rows($busca);


if ($query1 == 1){
	@mysqli_query($_SG['link'], "UPDATE ordem_servico SET situacao = '$situacao', descricao = '$descricao' WHERE os = '$os'");
    echo 1;
} else{
       @mysqli_query($_SG['link'], "INSERT INTO ordem_servico (os,situacao,descricao) VALUES ('$os','$situacao','$descricao')");
    echo 0;
}
