$(function () {

    carregar();
    carregarBancos();

    $('#btnEnviar').click(function () {
        $.post('agencias', $('form').serialize(), function () {
            carregar();
            $('form').each(function () {
                this.reset();
            });
        });
    });
    
    $("#agenciaForm input[name=numero_agencia]").mask("0000");
    $("#agenciaForm input[name=digito]").mask("0");
    
    $('#btnBuscar').click(function () {
        carregar();
    });
});

function carregar() {
    $.getJSON('agencias', $('form[role=search]').serialize()).success(function (registros) {
        window.templateTr = window.templateTr || $('#divTable table tbody').html();
        var trHtml = window.templateTr;
        var respHtml = "";
        registros.forEach(function (item) {
            respHtml += trHtml
                    .replace(/\{\{id\}\}/g, item.id)
                    .replace(/\{\{nome\}\}/g, item.nome)
                    .replace(/\{\{id_banco\}\}/g, item.nomeBanco)
                    .replace(/\{\{numero_agencia\}\}/g, item.numero_agencia)
                    .replace(/\{\{digito\}\}/g, item.digito)
                    .replace(/\{\{endereco\}\}/g, item.endereco)
        });
        $('#divTable table tbody').html(respHtml);
    });
}

function editar(id) {
    $.getJSON("agencias?id=" + id).success(function (data) {
        $("input[name=id]").val(data.id);
        $("select[name=id_banco]").val(data.id_banco);
        $("input[name=nome]").val(data.nome);
        $("input[name=numero_agencia]").val(data.numero_agencia);
        $("input[name=digito]").val(data.digito);
        $("input[name=endereco]").val(data.endereco);
    });
}

function excluir(id) {
    $.ajax("agencias?id=" + id, {
        type: "DELETE"
    }).success(function () {
        carregar();
    }
    );
}

function carregarBancos(executa) {
    $.getJSON('bancos').success(function (bancos) {
        var options = "";
        bancos.forEach(function (item) {
            options += '<option value="' + item.id + '">' + item.codigo_banco + ' - ' + item.nome + '</option>';
        });
        $('#agenciaForm select[name=id_banco]').html(options);
        if (executa)
            executa();
    });
}


function limparForm() {
    $("input[name=id]").val("");
    $("input[name=nome]").val("");
    $("input[name=numero_agencia]").val("");
    $("input[name=digito]").val("");
    $("input[name=endereco]").val("");
    carregarBancos();
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