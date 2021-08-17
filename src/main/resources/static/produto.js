

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
