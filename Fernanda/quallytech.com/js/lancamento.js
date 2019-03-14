window.onload = function () {

	var salvarJs = document.getElementById("salvar");
	salvarJs.onclick = function () {
        Salvar();
    };
	
};

function CriaRequest() {
    try {
        request = new XMLHttpRequest();
    } catch (IEAtual) {
        try {
            request = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (IEAntigo) {
            try {
                request = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (falha) {
                request = false;
            }
        }
    }
    if (!request)
        alert("Seu Navegador não suporta Ajax!");
    else
        return request;
}


function Salvar() {
    var xmlreq = CriaRequest();

    var os = document.getElementById("os");
    var situacao = document.getElementById("situacao");
    var descricao = document.getElementById("descricao");

    xmlreq.open("GET", "salvar.php?os=" + os.value +"&situacao="+situacao.value+"&descricao="+descricao.value, true);
    xmlreq.onreadystatechange = function () {
        if (xmlreq.readyState == 4) {
            if (xmlreq.status == 200) {
                var resposta = xmlreq.responseText;
								alert(resposta);

                if (resposta == 1) {
                    alert("Status da OS "+os.value+" atualizado para "+situacao.value);
                    window.location.href = ("lancamento.html");
                }
				if (resposta == 0) {
                    alert("Status da OS "+os.value+" cadastrado com sucesso." );
                    window.location.href = ("lancamento.html");
                }
            }
        }
    };
    xmlreq.send(null);
}
