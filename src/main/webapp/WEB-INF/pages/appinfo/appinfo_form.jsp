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
                    <form:form action="${pageContext.request.contextPath}/appinfo/update" method="POST" modelAttribute="appinfo" class="form-horizontal">

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
                                <label class="col-md-3 control-lable" for="url">URL:</label>
                                <div class="col-md-7">
                                    <form:input type="text" path="url" id="url" class="form-control input-sm" />
                                    <div class="has-error">
                                        <form:errors path="url" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="path">Път:</label>
                                <div class="col-md-7">
                                    <form:input type="text" path="path" id="path" class="form-control input-sm" />
                                    <div class="has-error">
                                        <form:errors path="path" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="protocol">Протокол:</label>
                                <div class="col-md-7">
                                    <form:input type="text" path="protocol" id="protocol" class="form-control input-sm" />
                                    <div class="has-error">
                                        <form:errors path="protocol" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="port">Порт</label>
                                <div class="col-md-7">
                                    <form:input type="text" path="port" id="port" class="form-control input-sm" />
                                    <div class="has-error">
                                        <form:errors path="port" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="hearthbeat_interval">Hearthbeat интервал</label>
                                <div class="col-md-7">
                                    <form:input type="text" path="hearthbeatInterval" id="hearthbeat_interval" class="form-control input-sm" />
                                    <div class="has-error">
                                        <form:errors path="hearthbeatInterval" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="unresponsive_interval">Unresponsive интервал</label>
                                <div class="col-md-7">
                                    <form:input type="text" path="unresponsiveInterval" id="unresponsive_interval" class="form-control input-sm" />
                                    <div class="has-error">
                                        <form:errors path="unresponsiveInterval" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="user_list">Списък потребители:</label>
                                <div class="col-md-7">
                                    <form:select id="user_list" path="userSet" items="${userList}" itemValue="id"
                                                 itemLabel="firstName" multiple="true"/>


                                    <div class="has-error">
                                        <form:errors path="userSet" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="notificationchannel_list">Нотификационни канали:</label>
                                <div class="col-md-7">
                                    <form:select id="notificationchannel_list" path="notificationChannelSet" items="${notificatonList}" itemValue="id"
                                                 itemLabel="channelName" multiple="true"/>


                                    <div class="has-error">
                                        <form:errors path="notificationChannelSet" class="help-inline"/>
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