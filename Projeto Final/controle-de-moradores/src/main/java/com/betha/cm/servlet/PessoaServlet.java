package com.betha.cm.servlet;

import com.betha.cm.common.util.Utils;
import com.betha.cm.dao.PessoaDao;
import com.betha.cm.model.Pessoa;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("pessoas")
public class PessoaServlet extends HttpServlet {

    private final PessoaDao dao = new PessoaDao();
    private final Pessoa pessoa = new Pessoa();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try (PrintWriter writer = response.getWriter()) {
            if (Utils.isNotEmpty(request.getParameter("id"))) {
                pessoa.setId(Utils.parseLong(request.getParameter("id")));
                writer.append(Utils.getJson(dao.buscar(pessoa)));
            } else {
                writer.append(Utils.convertListToJson(dao.buscarTodos(pessoa, request.getParameter("filter"))));
            }
        } catch (Exception ex) {
            Logger.getLogger(PessoaServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(500, ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try (PrintWriter writer = response.getWriter()) {
            Pessoa pessoaNew = new Pessoa();
            pessoaNew.parse(Utils.getParameterMap(request));
            if (Utils.isNotEmpty(request.getParameter("id"))) {
                pessoaNew = dao.atualizar(pessoaNew);
            } else {
                pessoaNew = dao.inserir(pessoaNew);
            }
            writer.append(Utils.getJson(pessoaNew));
        } catch (Exception ex) {
            Logger.getLogger(PessoaServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(500, ex.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Utils.isNotEmpty(request.getParameter("id"))) {
            try {
                pessoa.setId(Utils.parseLong(request.getParameter("id")));
                dao.deletar(pessoa);
                response.getWriter().append("NO CONTENT");
            } catch (Exception ex) {
                Logger.getLogger(PessoaServlet.class.getName()).log(Level.SEVERE, null, ex);
                response.sendError(500, ex.getMessage());
            }
        }
    }

}
