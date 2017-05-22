<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../fragments/header.jspf" %>
    <body>

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <div class="page-header">
                        <h1>
                            Мониторинг!
                        </h1>
                    </div>
                </div>
            </div>
            <div class="row">
<%@include file="../fragments/menu.jspf" %>
                <div class="col-md-10">
                    <a href="<c:url value="/appinfo/create" /> " class="btn btn-info" role="button">Нова нотификация</a>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>
                                    #
                                </th>
                                <th>
                                    Име
                                </th>
                                <th>
                                    URL
                                </th>
                                <th>
                                    Порт
                                </th>
                                <th>
                                    Hearthbeat интервал
                                </th>
                                <th>
                                    Unresponsive интервал
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="appinfo" items="${appinfolist}">
                                <tr>
                                    <td>
                                        1
                                    </td>
                                    <td>
                                        ${appinfo.name}
                                    </td>
                                    <td>
                                        ${appinfo.url}
                                    </td>
                                    <td>
                                        ${appinfo.port}
                                    </td>
                                    <td>
                                        ${appinfo.hearthbeatInterval}
                                    </td>
                                    <td>
                                        ${appinfo.unresponsiveInterval}
                                    </td>
                                    <td>
                                        <a href="<c:url value="/appinfo/edit/${appinfo.id}" />" class="btn btn-info" role="button">Редактирай</a>
                                    </td>
                                    <td>
                                        <form action="<c:url value="/appinfo/delete/${appinfo.id}" /> " method="POST">
                                            <button type="submit" class="btn btn-danger" role="button">Изтрий</button>
                                            <input type="hidden" name="${_csrf.parameterName}"
                                                   value="${_csrf.token}" />
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <script src="<c:url value="/static/js/jquery.min.js" />"></script>
        <script src="<c:url value="/static/js/bootstrap.min.js" />"></script>
        <script src="<c:url value="/static/js/scripts.js" />"></script>
    </body>
</html>