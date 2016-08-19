$(function () {

    carregar();

    $('#btnEnviar').click(function () {
        $.post('fornecedores', $('form').serialize(), function () {
            carregar();
            $('form').each(function () {
                this.reset();
            });
        });
    });

    $("#fornecedorForm input[name=telefone]").mask("(00) 0000-00009");

    $('#btnBuscar').click(function () {
        carregar();
    });
});

function carregar() {
    $.getJSON('fornecedores', $('form[role=search]').serialize()).success(function (registros) {
        window.templateTr = window.templateTr || $('#divTable table tbody').html();
        var trHtml = window.templateTr;
        var respHtml = "";
        registros.forEach(function (item) {
            respHtml += trHtml
                    .replace(/\{\{id\}\}/g, item.id)
                    .replace(/\{\{nome\}\}/g, item.nome)
                    .replace(/\{\{telefone\}\}/g, item.telefone)
                    .replace(/\{\{email\}\}/g, item.email)
        });
        $('#divTable table tbody').html(respHtml);
    });
}

function editar(id) {
    $.getJSON("fornecedores?id=" + id).success(function (data) {
        $("input[name=id]").val(data.id);
        $("input[name=nome]").val(data.nome);
        $("input[name=telefone]").val(data.telefone);
        $("input[name=email]").val(data.email);
    });
}

function excluir(id) {
    $.ajax("fornecedores?id=" + id, {
        type: "DELETE"
    }).success(function () {
        carregar();
    }
    );
}

function limparForm() {
    $("input[name=id]").val("");
    $("input[name=nome]").val("");
    $("input[name=telefone]").val("");
    $("input[name=email]").val("");
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