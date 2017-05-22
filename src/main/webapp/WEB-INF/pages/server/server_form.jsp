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
                <div class="col-md-8">
                    <form:form action="${pageContext.request.contextPath}/server/update" method="POST" modelAttribute="server" class="form-horizontal">

                        <form:hidden path="id" />
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="name">Име:</label>
                                <div class="col-md-7">
                                    <form:input type="text" path="name" id="name" class="form-control input-sm"/>
                                    <div class="has-error">
                                        <form:errors path="name" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="ipAddress">IP Адрес:</label>
                                <div class="col-md-7">
                                    <form:input type="text" path="ipAddress" id="ipAddress" class="form-control input-sm" />
                                    <div class="has-error">
                                        <form:errors path="ipAddress" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="domain">Домейн:</label>
                                <div class="col-md-7">
                                    <form:input type="text" path="domain" id="domain" class="form-control input-sm" />
                                    <div class="has-error">
                                        <form:errors path="domain" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="notification_policy">Ресурсни нотификации:</label>
                                <div class="col-md-7">
                                    <form:select id="notification_policy" path="notificationPolicy" items="${rsnotificationslist}" itemValue="id"
                                                 itemLabel="name">

                                    </form:select>
                                    <div class="has-error">
                                        <form:errors path="notificationPolicy" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="user_notification">Потребителски нотификации:</label>
                                <div class="col-md-7">
                                    <form:select id="user_notification" path="userSet" items="${userNotifications}" itemValue="id"
                                                 itemLabel="firstName" multiple="true"/>


                                    <div class="has-error">
                                        <form:errors path="userSet" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="notification_groups">Нотификационни групи:</label>
                                <div class="col-md-7">
                                    <form:select  id="notification_groups" path="notificationGroupSet" items="${notificationGroups}" itemValue="id"
                                                  itemLabel="name" multiple="true" />


                                    <div class="has-error">
                                        <form:errors path="notificationGroupSet" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                                    <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="is_active">Активен:</label>
                                <div class="col-md-7">
                                    <form:checkbox  id="is_active" path="active" itemValue="id"
                                                  itemLabel="name"/>


                                    <div class="has-error">
                                        <form:errors path="active" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-info" value="Send">Запази</button>
                        <input type="hidden" name="${_csrf.parameterName}"
                               value="${_csrf.token}" />
                    </form:form>
                </div>
                <div class="col-md-2">
                </div>
            </div>
        </div>
        <script src="<c:url value="/static/js/jquery.min.js" />"></script>
        <script src="<c:url value="/static/js/bootstrap.min.js" />"></script>
        <script src="<c:url value="/static/js/scripts.js" />"></script>
    </body>
</html>