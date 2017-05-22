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
                    <form:form action="${pageContext.request.contextPath}/resourcePolicy/update" method="POST" modelAttribute="rspolicy" class="form-horizontal">
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
                                <label class="col-md-3 control-lable" for="cpu_warn_level">CPU Ниво warn:</label>
                                <div class="col-md-7">
                                    <form:input type="text" path="cpuResourceConfig.warnLevel" id="cpu_warn_level" class="form-control input-sm"/>
                                    <div class="has-error">
                                        <form:errors path="cpuResourceConfig.warnLevel" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="cpu_critical_level">CPU Ниво critical:</label>
                                <div class="col-md-7">
                                    <form:input type="text" path="cpuResourceConfig.criticalLevel" id="cpu_critical_level" class="form-control input-sm"/>
                                    <div class="has-error">
                                        <form:errors path="cpuResourceConfig.criticalLevel" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="cpu_notification_interval">CPU нотиф. интервал:</label>
                                <div class="col-md-7">
                                    <form:input type="text" path="cpuResourceConfig.notificationInverval" id="cpu_notification_interval" class="form-control input-sm"/>
                                    <div class="has-error">
                                        <form:errors path="cpuResourceConfig.notificationInverval" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="memory_warn_level">Памет Ниво warn:</label>
                                <div class="col-md-7">
                                    <form:input type="text" path="memoryResourceConfig.warnLevel" id="memory_warn_level" class="form-control input-sm"/>
                                    <div class="has-error">
                                        <form:errors path="memoryResourceConfig.warnLevel" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="memory_critical_level">Памет Ниво critical:</label>
                                <div class="col-md-7">
                                    <form:input type="text" path="memoryResourceConfig.criticalLevel" id="memory_critical_level" class="form-control input-sm"/>
                                    <div class="has-error">
                                        <form:errors path="memoryResourceConfig.criticalLevel" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="memory_notification_usage">Памет нотиф. интервал:</label>
                                <div class="col-md-7">
                                    <form:input type="text" path="memoryResourceConfig.notificationInverval" id="memory_notification_usage" class="form-control input-sm"/>
                                    <div class="has-error">
                                        <form:errors path="memoryResourceConfig.notificationInverval" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="disk_usage_warn_level">Disk % Ниво warn:</label>
                                <div class="col-md-7">
                                    <form:input type="text" path="diskUsageResourceConfig.warnLevel" id="disk_usage_warn_level" class="form-control input-sm"/>
                                    <div class="has-error">
                                        <form:errors path="diskUsageResourceConfig.warnLevel" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="disk_usage_critical_level">Disk % Ниво critical:</label>
                                <div class="col-md-7">
                                    <form:input type="text" path="diskUsageResourceConfig.criticalLevel" id="disk_usage_critical_level" class="form-control input-sm"/>
                                    <div class="has-error">
                                        <form:errors path="diskUsageResourceConfig.criticalLevel" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="disk_usage_notification_interval">Disk % нотиф. интервал:</label>
                                <div class="col-md-7">
                                    <form:input type="text" path="diskUsageResourceConfig.notificationInverval" id="disk_usage_notification_interval" class="form-control input-sm"/>
                                    <div class="has-error">
                                        <form:errors path="diskUsageResourceConfig.notificationInverval" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="server_unresponsive_interval">Сървър unresponsive интервал:</label>
                                <div class="col-md-7">
                                    <form:input type="text" path="serverUnresponsiveInterval" id="server_unresponsive_interval" class="form-control input-sm"/>
                                    <div class="has-error">
                                        <form:errors path="serverUnresponsiveInterval" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="server_set">Сървъри:</label>
                                <div class="col-md-7">
                                    <form:select  path="serverSet" items="${serverlist}" id="server_set" class="form-control input-sm" 
                                                  itemValue="id" itemLabel="name" multiple="true"/>
                                    <div class="has-error">
                                        <form:errors path="serverSet" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="notification_channel_set">Нотификационни канали:</label>
                                <div class="col-md-7">
                                    <form:select  path="notificationChannelSet" items="${notificationchannellist}" id="notification_channel_set" class="form-control input-sm" 
                                                  itemValue="id" itemLabel="channelName" multiple="true"/>
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