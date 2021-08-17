function mostrarContainerCad(){
    var cont = document.getElementById("container-cadastrar");
    cont.removeAttribute("hidden");
    document.getElementById("container-alterar").setAttribute("hidden","hidden");
    document.getElementById("container-deletar").setAttribute("hidden","hidden");
    document.getElementById("container-buscar").setAttribute("hidden","hidden");
    document.getElementById("container-listar").setAttribute("hidden","hidden");
}

function mostrarContainerAlt(){
    var cont = document.getElementById("container-alterar");
    cont.removeAttribute("hidden");
    document.getElementById("container-cadastrar").setAttribute("hidden","hidden");
    document.getElementById("container-deletar").setAttribute("hidden","hidden");
    document.getElementById("container-buscar").setAttribute("hidden","hidden");
    document.getElementById("container-listar").setAttribute("hidden","hidden");
}

function mostrarContainerDel(){
    var cont = document.getElementById("container-deletar");
    cont.removeAttribute("hidden");
    document.getElementById("container-cadastrar").setAttribute("hidden","hidden");
    document.getElementById("container-alterar").setAttribute("hidden","hidden");
    document.getElementById("container-buscar").setAttribute("hidden","hidden");
    document.getElementById("container-listar").setAttribute("hidden","hidden");
}

function mostrarContainerBus(){
    var cont = document.getElementById("container-buscar");
    cont.removeAttribute("hidden");
    document.getElementById("container-cadastrar").setAttribute("hidden","hidden");
    document.getElementById("container-alterar").setAttribute("hidden","hidden");
    document.getElementById("container-deletar").setAttribute("hidden","hidden");
    document.getElementById("container-listar").setAttribute("hidden","hidden");
}

function mostrarContainerLis(){
    var cont = document.getElementById("container-listar");
    cont.removeAttribute("hidden");
    document.getElementById("container-cadastrar").setAttribute("hidden","hidden");
    document.getElementById("container-alterar").setAttribute("hidden","hidden");
    document.getElementById("container-deletar").setAttribute("hidden","hidden");
    document.getElementById("container-buscar").setAttribute("hidden","hidden");
}

function mostrarBuscar(){
    var linha = document.getElementById("linha-buscar-vendedor").value;
    if(linha != "1"){
        var cont = document.getElementById("container-buscar");
            cont.removeAttribute("hidden");
            document.getElementById("container-tabela-buscar-vendedor").removeAttribute("hidden");
    }else{
        document.getElementById("container-buscar").setAttribute("hidden","hidden");
        document.getElementById("container-tabela-buscar-vendedor").setAttribute("hidden","hidden");
    }

}

function vendedorInexistente(){
    var linha = document.getElementById("linha-buscar-vendedor2").value;
     if(linha != "2"){
        document.getElementById("container-vendedor-inexistente").setAttribute("hidden");
    }
    else{
        document.getElementById("container-vendedor-inexistente").removeAttribute("hidden");
        var cont = document.getElementById("container-buscar");
                    cont.removeAttribute("hidden");
    }
}

function tabelasVendedorMostrar(){
    var linha = document.getElementById("linha-buscar-vendedor3").value;
    var linha2 = document.getElementById("linha-buscar-vendedor4").value;
    var cont = document.getElementById("container-listar");

    if(linha !=""){
        document.getElementById("container-tabela-vendas-vendedor").removeAttribute("hidden");
        cont.removeAttribute("hidden");
    }else if(linha2 !=""){
        document.getElementById("container-tabela-valor-vendedor").removeAttribute("hidden");
        cont.removeAttribute("hidden");
    }else{
        document.getElementById("container-tabela-vendas-vendedor").setAttribute("hidden");
        document.getElementById("container-tabela-valor-vendedor").setAttribute("hidden");
    }
}


