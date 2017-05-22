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
                    <a href="<c:url value="/resourcePolicy/create" /> " class="btn btn-info" role="button">Нова полица</a>
                    <table class="table-striped">
                        <thead>
                            <tr>
                                <th>
                                    #
                                </th>
                                <th>
                                    Име
                                </th>
                                <th>
                                    CPU Ниво warn
                                </th>
                                <th>
                                    CPU Ниво critical
                                </th>
                                <th>
                                    CPU нотиф. интервал
                                </th>

                                <th>
                                    Памет Ниво warn
                                </th>
                                <th>
                                    Памет Ниво critical
                                </th>
                                <th>
                                    Памет нотиф. интервал
                                </th>
                                <th>
                                    Disk % Ниво warn
                                </th>
                                <th>
                                    Disk % Ниво critical
                                </th>
                                <th>
                                    Disk % нотиф. интервал
                                </th>
                                <th>
                                    Сървър unresponsive интервал
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="rsnotification" items="${rsnotificationlist}">
                                <tr>
                                    <td>
                                        #
                                    </td>
                                    <td>
                                        ${rsnotification.name}
                                    </td>
                                    <td>
                                        ${rsnotification.cpuResourceConfig.warnLevel}
                                    </td>
                                    <td>
                                        ${rsnotification.cpuResourceConfig.criticalLevel}
                                    </td>
                                    <td>
                                        ${rsnotification.cpuResourceConfig.notificationInverval}
                                    </td>
                                    <td>
                                        ${rsnotification.memoryResourceConfig.warnLevel}
                                    </td>
                                    <td>
                                        ${rsnotification.memoryResourceConfig.criticalLevel}
                                    </td>
                                    <td>
                                        ${rsnotification.memoryResourceConfig.notificationInverval}
                                    </td>
                                    <td>
                                        ${rsnotification.diskUsageResourceConfig.warnLevel}
                                    </td>
                                    <td>
                                        ${rsnotification.diskUsageResourceConfig.criticalLevel}
                                    </td>
                                    <td>
                                        ${rsnotification.diskUsageResourceConfig.notificationInverval}
                                    </td>
                                    <td>
                                        ${rsnotification.serverUnresponsiveInterval}
                                    </td>
                                    <td>
                                        <a href="<c:url value="/resourcePolicy/edit/${rsnotification.id}" />" class="btn btn-info" role="button">Редактирай</a>
                                    </td>
                                    <td>
                                        <form action="<c:url value="/resourcePolicy/delete/${rsnotification.id}" /> " method="POST">
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