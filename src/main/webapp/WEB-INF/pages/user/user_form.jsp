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
                    <form:form action="${pageContext.request.contextPath}/user/update" method="POST" modelAttribute="user" class="form-horizontal">

                        <form:hidden path="id" />
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="first_name">Име:</label>
                                <div class="col-md-7">
                                    <form:input type="text" path="firstName" id="first_name" class="form-control input-sm"/>
                                    <div class="has-error">
                                        <form:errors path="firstName" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="last_name">Фамилия:</label>
                                <div class="col-md-7">
                                    <form:input type="text" path="lastName" id="last_name" class="form-control input-sm" />
                                    <div class="has-error">
                                        <form:errors path="lastName" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="password">Парола:</label>
                                <div class="col-md-7">
                                    <form:password path="password" id="password" class="form-control input-sm" />
                                    <div class="has-error">
                                        <form:errors path="password" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="phone">Телефон:</label>
                                <div class="col-md-7">
                                    <form:input type="text" path="phone" id="phone" class="form-control input-sm" />
                                    <div class="has-error">
                                        <form:errors path="phone" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="email">Имейл:</label>
                                <div class="col-md-7">
                                    <form:input type="text" path="email" id="email" class="form-control input-sm" />
                                    <div class="has-error">
                                        <form:errors path="email" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="server_list">Сървърни нотифицакии:</label>
                                <div class="col-md-7">
                                    <form:select id="server_list" path="serverSet" items="${serverList}" itemValue="id"
                                                 itemLabel="name" multiple="true" />


                                    <div class="has-error">
                                        <form:errors path="serverSet" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="notificationgroup_list">Нотификационни групи:</label>
                                <div class="col-md-7">
                                    <form:select id="notificationgroup_list" path="notificationGroupSet" items="${notificationGroupList}" itemValue="id"
                                                 itemLabel="name" multiple="true" />


                                    <div class="has-error">
                                        <form:errors path="notificationGroupSet" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="appinfo_list">Нотификации за достъпност до сървъри:</label>
                                <div class="col-md-7">
                                    <form:select id="appinfo_list" path="appInfoSet" items="${appinfoList}" itemValue="id"
                                                 itemLabel="name" multiple="true" />


                                    <div class="has-error">
                                        <form:errors path="appInfoSet" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="roles_list">Роли в системата:</label>
                                <div class="col-md-7">
                                    <form:select id="roles_list" path="userRoles" items="${rolesList}" itemValue="id"
                                                 itemLabel="role" multiple="true"/>


                                    <div class="has-error">
                                        <form:errors path="userRoles" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-info" value="Send" >Запази</button>
                        <input type="hidden" name="${_csrf.parameterName}"
                               value="${_csrf.token}" />
                    </form:form>
                </div>
            </div>
        </div>
        <script src="<c:url value="/static/js/jquery.min.js" />"></script>
        <script src="<c:url value="/static/js/bootstrap.min.js" />"></script>
        <script src="<c:url value="/static/js/scripts.js" />"></script>
    </body>
</html>