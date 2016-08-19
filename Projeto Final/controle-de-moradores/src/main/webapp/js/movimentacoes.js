$(function () {
    $("input[name=consulta]").val(dataHj);
    
    carregar();
    carregarFornecedores();
    
    $('#btnEnviarMov').click(function () {
        $.post('movimentacoes', $('form').serialize(), function () {
            $('#chequeForm').each(function () {
                this.reset();
            });
        });
    });

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

function carregarMovimentacoes(id) {
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
    $('#movimentacoes').modal('hide')
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
        $('#movimentacoes').modal('hide')
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