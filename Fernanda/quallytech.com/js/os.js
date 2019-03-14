window.onload = function () {

    var buscarJs = document.getElementById("buscar");
    buscarJs.onclick = function () {
        Buscar();
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

function Buscar() {
    var xmlreq = CriaRequest();

    var os = document.getElementById("os");
    xmlreq.open("GET", "buscar.php?os=" + os.value, true);

    xmlreq.onreadystatechange = function () {
        if (xmlreq.readyState == 4) {
            if (xmlreq.status == 200) {
                var resposta = xmlreq.responseText;
                if (resposta == 0) {
                    alert("OS não encontrada. Aguarde até a entrada dos dados no sistema ou entre em contato.");
					window.location.href = "index.html";
                }else{
				   alert(resposta);
				   window.location.href = "index.html";
				}
            }
        }
    };
    xmlreq.send(null);
}

