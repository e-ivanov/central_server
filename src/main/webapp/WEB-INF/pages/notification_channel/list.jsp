<%-- 
    Document   : list
    Created on : Mar 20, 2017, 7:20:28 AM
    Author     : killer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
        <title>Bootstrap 3, from LayoutIt!</title>

        <meta name="description" content="Source code generated using layoutit.com">
        <meta name="author" content="LayoutIt!">

        <link href="<c:url value="/static/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="/static/css/style.css" />" rel="stylesheet">
        <script>var ctx = "${pageContext.request.contextPath}"</script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/mustache.js/2.3.0/mustache.min.js"></script>


    </head>
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
                <div class="col-md-2">
                    <ul>
                        <li><a href="<c:url value="/server/list" />">Сървъри</a></li>
                        <li><a href="<c:url value="/server/create" />">Нов сървър</a></li>
                        <li><a href="<c:url value="/appinfo/list" />">Пинг конфиг.</a></li>
                        <li><a href="<c:url value="/appinfo/create" />">Нова пинг конфиг.</a></li>
                        <li><a href="<c:url value="/notificationChannel/list" />">Нотификационни канали</a></li>
                        <li><a href="<c:url value="/notificationgroup/list" />">Нотификационни групи</a></li>
                        <li><a href="<c:url value="/notificationgroup/create" />">Нова нотиф. група</a></li>
                        <li><a href="<c:url value="/resourcePolicy/list" />">Списък ресурсни конфиг.</a></li>
                        <li><a href="<c:url value="/resourcePolicy/create" />">Нова ресурсна конфиг.</a></li>
                        <li><a href="<c:url value="/user/list" />">Потребители</a></li>
                        <li><a href="<c:url value="/user/create" />">Нов потребител</a></li>
                    </ul>
                </div>
                <div class="col-md-8">
                    <div class="panel-group" id="accordion1">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion1" href="#collapseTwo">Email(${fn:length(emailnotificationlist)})
                                    </a>
                                </h4>
                            </div>
                            <div id="collapseTwo" class="panel-collapse collapse">

                                <div class="panel-body">
                                    <div class="panel-body">
                                        <div class="panel-group" id="emailAccordion">

                                            <a id="modal-910482" href="#new_main_model_form_container" role="button" class="btn btn-info" data-toggle="modal">Добави нов имейл канал</a>

                                            <div class="modal fade" id="new_main_model_form_container" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">                                                                                                                                                                  

                                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                                ×
                                                            </button>
                                                            <h4 class="modal-title" id="myModalLabel">                                                                                                                                                                                        
                                                                Нов имейл канал
                                                            </h4>
                                                        </div>
                                                        <div class="modal-body">
                                                            <form:form modelAttribute="newEmailNotification" id="new_notification_form" action="${pageContext.request.contextPath}/notificationChannel/create/email" method="POST" class="form-horizontal" >
                                                                <div class="row">
                                                                    <div class="form-group col-md-12">
                                                                        <label class="col-md-3 control-lable" for="new_channel_name">Име:</label>
                                                                        <div class="col-md-7">
                                                                            <form:input type="text" path="channelName" id="new_channel_name" class="form-control input-sm"/>
                                                                            <div class="has-error">
                                                                                <form:errors path="channelName" class="help-inline"/>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="row">
                                                                    <div class="form-group col-md-12">
                                                                        <label class="col-md-3 control-lable" for="new_email_address">Имейл адрес:</label>
                                                                        <div class="col-md-7">
                                                                            <form:input type="text" path="emailAddress" id="new_email_address" class="form-control input-sm"/>
                                                                            <div class="has-error">
                                                                                <form:errors path="emailAddress" class="help-inline"/>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="modal-footer">

                                                                    <form:button class="btn btn-default" data-dismiss="modal" >Отказ</form:button>
                                                                    <form:button id="submit_new_email_channel" class="btn btn-primary" data-dismiss="modal" >Запази</form:button>
                                                                    </div>
                                                                    <input type="hidden" name="${_csrf.parameterName}"
                                                                       value="${_csrf.token}" />
                                                            </form:form>
                                                        </div>

                                                    </div>

                                                </div>
                                            </div>




                                            <div id="mail_list_container">
                                                <c:forEach var="emailChannel" items="${emailnotificationlist}" varStatus="loop">
                                                    <div class="panel">
                                                        <a data-toggle="collapse" data-parent="#emailAccordion" href="#collapseEmail${emailChannel.id}">${emailChannel.emailAddress}
                                                        </a>
                                                        <div id="#collapseEmail${emailChannel.id}" class="panel-collapse collapse">
                                                            <div class="panel-body">
                                                                <form action="${pageContext.request.contextPath}/notificationChannel/update" method="POST" class="form-horizontal" >
                                                                    <input type="hidden" name="id" value="${emailChannel.id}" />
                                                                    <div class="row">
                                                                        <div class="form-group col-md-12">
                                                                            <label class="col-md-3 control-lable" for="name">Име:</label>
                                                                            <div class="col-md-7">
                                                                                <input type="text" name="channelName" value="${emailChannel.channelName}" id="name" class="form-control input-sm"/>
                                                                                <div class="has-error">
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="row">
                                                                        <div class="form-group col-md-12">
                                                                            <label class="col-md-3 control-lable" for="email_address">Имейл адрес:</label>
                                                                            <div class="col-md-7">
                                                                                <input type="text" name="emailAddress" value="${emailChannel.emailAddress}" id="email_address" class="form-control input-sm"/>
                                                                                <div class="has-error">
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <button type="submit" class="btn btn-info" value="Send">Запази</button>
                                                                    <button type="submit" class="btn btn-danger" value="Delete">Изтрий</button>
                                                                </form>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                </div>
            </div>
        </div>

        <script src="<c:url value="/static/js/jquery.min.js" />"></script>
        <script src="<c:url value="/static/js/bootstrap.min.js" />"></script>
        <script src="<c:url value="/static/js/scripts.js" />"></script>
        <script src="<c:url value="/static/js/create_notifications.js" />"></script>
    </body>
</html>