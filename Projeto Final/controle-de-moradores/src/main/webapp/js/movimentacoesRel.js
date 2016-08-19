$(function () {

    carregarFornecedores();
    carregarCheques();
    carregarMovimentacoes();

    $('#btnEnviarMov').click(function () {
        $.post('movimentacoes', $('form').serialize(), function () {
            carregarMovimentacoes();
            $('#chequeForm').each(function () {
                this.reset();
            });
        });
    });

    $("input[name=consulta]").val(dataHj);

    $('#btnBuscar').click(function () {
        carregarMovimentacoes();
    });
});

function carregarMovimentacoes() {
    var id = $('select[name=filter]').val();
    $.getJSON('movimentacoes?filter=' + id).success(function (registros) {
        window.templateTre = window.templateTre || $('#divTMov table tbody').html();
        var trHtml = window.templateTre;
        var respHtml = "";
        registros.forEach(function (item) {
            respHtml += trHtml
                    .replace(/\{\{id\}\}/g, item.id)
                    .replace(/\{\{id_cheque\}\}/g, item.id_cheque)
                    .replace(/\{\{data_movimentacao\}\}/g, item.data_movimentacao)
                    .replace(/\{\{tipo_movimentacao\}\}/g, tipoMovimentacao(item.tipo_movimentacao))
                    .replace(/\{\{id_fornecedores\}\}/g, item.nomeFornecedor)
        });
        $('#divTMov table tbody').html(respHtml);
    });
}

function carregarCheques(executa) {
    $.getJSON('cheques').success(function (cheques) {
        var options = "";
        cheques.forEach(function (item) {
            options += '<option value="' + item.id + '">' + item.nome + '</option>';
        });
        $('select[name=filter]').html(options);
        if (executa)
            executa();
    });
}

function carregarFornecedores(executa) {
    $.getJSON('fornecedores').success(function (clientes) {
        var options = '<option value="0">Selecione o fornecedor</option>';
        clientes.forEach(function (item) {
            options += '<option value="' + item.id + '">' + item.nome + '</option>';
        });
        $('#movimentoForm select[name=id_fornecedores]').html(options);
        if (executa)
            executa();
    });
}

function editarMov(id) {
    $.getJSON("movimentacoes?id=" + id).success(function (data) {
        $("input[name=id]").val(data.id);
        $("input[name=id_cheque]").val(data.id_cheque);
        $("input[name=data_movimentacao]").val(data.data_movimentacao);
        $("select[name=tipo_movimentacao]").val(data.tipo_movimentacao);
        $("select[name=id_fornecedores]").val(data.id_fornecedores);
    });
}

function carregaCheque(id) {
    $("input[name=id]").val("");
    $("input[name=id_cheque]").val(id);
    $("input[name=data_movimentacao]").val("");
    $("select[name=tipo_movimentacao]").val("");
    $("select[name=id_fornecedores]").val("");
}

function excluirMov(id) {
    $.ajax("movimentacoes?id=" + id, {
        type: "DELETE"
    }).success(function () {
        carregarMovimentacoes();
    }
    );
}

function tipoMovimentacao(tipo) {
    if (tipo == '1') {
        return 'Recebido';
    }
    if (tipo == '2') {
        return 'Depositado';
    }
    if (tipo == '3') {
        return 'Repassado para terceiros';
    }
    if (tipo == '4') {
        return 'Devolvido';
    }
    if (tipo == '5') {
        return 'Realizado custodia';
    }
    if (tipo == '6') {
        return 'Roubado';
    }
}

function dataHj() {
    var now = new Date
    var ano = now.getFullYear();
    var mes = now.getMonth() + 1;
    var dia = now.getDate();
    if (mes < 10) {
        mes = "0" + mes
    }
    if (dia < 10) {
        dia = "0" + dia
    }
    return"" + ano + "-" + mes + "-" + dia;
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