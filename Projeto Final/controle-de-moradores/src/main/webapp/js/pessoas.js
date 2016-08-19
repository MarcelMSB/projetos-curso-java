$(function () {

    carregar();

    $('#btnEnviar').click(function () {
        $.post('pessoas', $('form').serialize(), function (data) {
            carregar();
            $('form').each(function () {
                this.reset();
            });
        });
    });
    
//    $("#clienteForm input[name=telefone]").mask("(00) 0000-00009");
//    $("#clienteForm input[name=numero]").mask("00000");

    $('#btnBuscar').click(function () {
        carregar();
    });
});

function carregar() {
    $.getJSON('pessoas', $('form[role=search]').serialize()).success(function (registros) {
        window.templateTr = window.templateTr || $('#divTable table tbody').html();
        var trHtml = window.templateTr;
        var respHtml = "";
        registros.forEach(function (item) {
            respHtml += trHtml
                    .replace(/\{\{id\}\}/g, item.id)
                    .replace(/\{\{nome\}\}/g, item.nome)
                    .replace(/\{\{sexo\}\}/g, item.sexo)
                    .replace(/\{\{cpf\}\}/g, item.cpf)
                    .replace(/\{\{rg\}\}/g, item.rg)
                    .replace(/\{\{dataNascimento\}\}/g, item.dataNascimento)
                    .replace(/\{\{telefoneFixo\}\}/g, item.telefoneFixo)
                    .replace(/\{\{telefoneCelular\}\}/g, item.telefoneCelular)
                    .replace(/\{\{email\}\}/g, item.email)
                    .replace(/\{\{situacaoAtual\}\}/g, item.situacaoAtual)
        });
        $('#divTable table tbody').html(respHtml);
    });
}

function editar(id) {
    $.getJSON("pessoas?id=" + id).success(function (data) {
        $("input[name=id]").val(data.id);
        $("input[name=nome]").val(data.nome);
        $("input[name=sexo]").val(data.sexo);
        $("input[name=cpf]").val(data.cpf);
        $("input[name=rg]").val(data.rg);
        $("input[name=dataNascimento]").val(data.dataNascimento);
        $("input[name=telefoneFixo]").val(data.telefoneFixo);
        $("input[name=telefoneCelular]").val(data.telefoneCelular);
        $("input[name=email]").val(data.email);
    });
}

function excluir(id) {
    $.ajax("pessoas?id=" + id, {
        type: "DELETE"
    }).success(function () {
        carregar();
    }
    );
}

function limparForm() {
        $("input[name=id]").val("");
        $("input[name=nome]").val("");
        $("input[name=sexo]").val("");
        $("input[name=cpf]").val("");
        $("input[name=rg]").val("");
        $("input[name=dataNascimento]").val("");
        $("input[name=telefoneFixo]").val("");
        $("input[name=telefoneCelular]").val("");
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