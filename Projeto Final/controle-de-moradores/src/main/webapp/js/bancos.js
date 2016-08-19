$(function () {

    carregar();

    $('#btnEnviar').click(function () {
        $.post('bancos', $('form').serialize(), function () {
            carregar();
            $('form').each(function () {
                this.reset();
            });
        });
    });
    
    $("#bancoForm input[name=codigo_banco]").mask("00000");
    
    $('#btnBuscar').click(function () {
        carregar();
    });
});

function carregar() {
    $.getJSON('bancos', $('form[role=search]').serialize()).success(function (registros) {
        window.templateTr = window.templateTr || $('#divTable table tbody').html();
        var trHtml = window.templateTr;
        var respHtml = "";
        registros.forEach(function (item) {
            respHtml += trHtml
                    .replace(/\{\{id\}\}/g, item.id)
                    .replace(/\{\{codigo_banco\}\}/g, item.codigo_banco)
                    .replace(/\{\{nome\}\}/g, item.nome);
        });
        $('#divTable table tbody').html(respHtml);
    });
}

function editar(id) {
    $.getJSON("bancos?id=" + id).success(function (data) {
        $("input[name=id]").val(data.id);
        $("input[name=codigo_banco]").val(data.codigo_banco);
        $("input[name=nome]").val(data.nome);
    });
}

function excluir(id) {
    $.ajax("bancos?id=" + id, {
        type: "DELETE"
    }).success(function () {
        carregar();
    }
    );
}

function limparForm() {
        $("input[name=id]").val("");
        $("input[name=codigo_banco]").val("");
        $("input[name=nome]").val("");
}

function searchKeyPress(e) {
    e = e || window.event;
    if (e.keyCode == 13)
    {
        carregar();
        return false;
    }
    return true;
}