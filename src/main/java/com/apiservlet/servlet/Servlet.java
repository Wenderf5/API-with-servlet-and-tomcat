package com.apiservlet.servlet;

import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/api")
public class Servlet extends HttpServlet {
    private Gson gson = new Gson();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String name = req.getParameter("name");
        if (name == null) {
            Response response = new Response(HttpServletResponse.SC_BAD_REQUEST, "Erro: O parâmetro 'name' é obrigatório na URL para a acessar a rota.");
            String responseJson = gson.toJson(response);

            res.setContentType("application/json");
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res.getWriter().write(responseJson);
            return;
        }
        Response response = new Response(HttpServletResponse.SC_OK, "Olá " + name + ", você acessou a rota GET!");
        String responseJson = gson.toJson(response);

        res.setContentType("application/json");
        res.setStatus(HttpServletResponse.SC_OK);
        res.getWriter().write(responseJson);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String body = getRequestBody(req);
        Response response = new Response(HttpServletResponse.SC_OK, "Dados recebidos via POST: " + (body.isEmpty() ? "Nenhum dado enviado" : body));
        String responseJson = gson.toJson(response);

        res.setContentType("application/json");
        res.setStatus(HttpServletResponse.SC_OK);
        res.getWriter().write(responseJson);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String id = req.getParameter("id");
        if (id == null) {
            Response response = new Response(HttpServletResponse.SC_BAD_REQUEST, "Erro: O parâmetro 'id' é obrigatório na URL para a atualização.");
            String responseJson = gson.toJson(response);

            res.setContentType("application/json");
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res.getWriter().write(responseJson);
            return;
        }
        Response response = new Response(HttpServletResponse.SC_OK, "Atualização via PUT para o ID " + id);
        String responseJson = gson.toJson(response);

        res.setContentType("application/json");
        res.setStatus(HttpServletResponse.SC_OK);
        res.getWriter().write(responseJson);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String id = req.getParameter("id");
        if (id == null) {
            Response response = new Response(HttpServletResponse.SC_BAD_REQUEST, "Erro: O parâmetro 'id' é obrigatório na URL para a exclusão.");
            String responseJson = gson.toJson(response);

            res.setContentType("application/json");
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res.getWriter().write(responseJson);
            return;
        }
        Response response = new Response(HttpServletResponse.SC_OK, "Recurso com ID " + id + " deletado com sucesso!");
        String responseJson = gson.toJson(response);

        res.setContentType("application/json");
        res.setStatus(HttpServletResponse.SC_OK);
        res.getWriter().write(responseJson);
    }

    private String getRequestBody(HttpServletRequest req) throws IOException {
        try (BufferedReader reader = req.getReader()) {
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }
}
