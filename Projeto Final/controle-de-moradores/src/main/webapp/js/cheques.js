$(function () {

    carregar();
    carregarAgencia();
    carregarCliente();
    
    $('#btnEnviar').click(function () {
        $.post('cheques', $('form').serialize(), function () {
            carregar();
            $('form').each(function () {
                this.reset();
            });
        });
    });
    
    $("#chequeForm input[name=numero]").mask("00000000");
    $("#chequeForm input[name=valor]").mask("#######0.00", {reverse: true});
    
    $('#btnBuscar').click(function () {
        carregar();
    });
});

function carregar() {
    $.getJSON('cheques', $('form[role=search]').serialize()).success(function (registros) {
        window.templateTr = window.templateTr || $('#divTable table tbody').html();
        var trHtml = window.templateTr;
        var respHtml = "";
        registros.forEach(function (item) {
            respHtml += trHtml
                    .replace(/\{\{id\}\}/g, item.id)
                    .replace(/\{\{numero\}\}/g, item.numero)
                    .replace(/\{\{id_cliente\}\}/g, item.nomeCliente)
                    .replace(/\{\{id_agencia\}\}/g, item.nomeAgencia)
                    .replace(/\{\{nome\}\}/g, item.nome)
                    .replace(/\{\{data_recebimento\}\}/g, item.data_recebimento)
                    .replace(/\{\{data_vencimento\}\}/g, item.data_vencimento)
                    .replace(/\{\{valor\}\}/g, item.valor)
        });
        $('#divTable table tbody').html(respHtml);
    });
}

function editar(id) {
    $.getJSON("cheques?id=" + id).success(function (data) {
        $("input[name=id]").val(data.id);
        $("input[name=numero]").val(data.numero);
        $("select[name=id_cliente]").val(data.id_cliente);
        $("input[name=nome]").val(data.nome);
        $("select[name=id_agencia]").val(data.id_agencia);
        $("input[name=data_recebimento]").val(data.data_recebimento);
        $("input[name=data_vencimento]").val(data.data_vencimento);
        $("input[name=valor]").val(data.valor);
    });
}

function excluir(id) {
    $.ajax("cheques?id=" + id, {
        type: "DELETE"
    }).success(function () {
        carregar();
    }
    );
}

function carregarAgencia(executa) {
    $.getJSON('agencias').success(function (agencias) {
        var options = "";
        agencias.forEach(function (item) {
            options += '<option value="' + item.id + '">' + item.nomeBanco + ' - ' + item.numero_agencia + ' - ' + item.nome + '</option>';
        });
        $('#chequeForm select[name=id_agencia]').html(options);
        if (executa)
            executa();
    });
}

function carregarCliente(executa) {
    $.getJSON('clientes').success(function (clientes) {
        var options = "";
        clientes.forEach(function (item) {
            options += '<option value="' + item.id + '">' + item.nome + '</option>';
        });
        $('#chequeForm select[name=id_cliente]').html(options);
        if (executa)
            executa();
    });
}

function limparForm() {
    $("input[name=id]").val("");
    $("input[name=numero]").val("");
    $("input[name=nome]").val("");
    $("select[name=id_agencia]").val("");
    $("input[name=data_recebimento]").val("");
    $("input[name=data_vencimento]").val("");
    $("input[name=valor]").val("");
    carregarAgencia();
    carregarCliente();
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