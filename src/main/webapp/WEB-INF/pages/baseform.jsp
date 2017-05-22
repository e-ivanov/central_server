<%-- 
    Document   : dashboard
    Created on : Mar 13, 2017, 10:59:20 AM
    Author     : killer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Bootstrap 3, from LayoutIt!</title>

        <meta name="description" content="Source code generated using layoutit.com">
        <meta name="author" content="LayoutIt!">

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">

    </head>
    <body>

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <div class="row">
                        <div class="col-md-4">
                        </div>
                        <div class="col-md-4">
                            <form:form action="${pageContext.request.contextPath}/server/edit" method="POST" modelAttribute="server" class="form-horizontal">

                                <form:hidden path="id" />
                                <div class="row">
                                    <div class="form-group col-md-12">
                                        <label class="col-md-3 control-lable" for="name">Name:</label>
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
                                        <label class="col-md-3 control-lable" for="ipAddress">IP Address:</label>
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
                                        <label class="col-md-3 control-lable" for="domain">Domain:</label>
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
                                        <label class="col-md-3 control-lable" for="thresholdNotificationSet">Threshold Notifications:</label>
                                        <div class="col-md-7">
                                            <form:select path="thresholdNotificationSet" items="${notifications}" itemValue="id"
                                                         itemLabel="name">

                                            </form:select>
                                            <div class="has-error">
                                                <form:errors path="thresholdNotificationSet" class="help-inline"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="form-group col-md-12">
                                        <label class="col-md-3 control-lable" for="thresholdValueSet">Threshold Values</label>
                                        <div class="col-md-7">
                                            <form:checkboxes path="thresholdValueSet"  items="${thresholdValueSet}" itemValue="id"
                                                         itemLabel="name" />
                                            <div class="has-error">
                                                <form:errors path="thresholdValueSet" class="help-inline"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                            <input type="submit" value="Send" />
                            </form:form>
                        </div>
                        <div class="col-md-4">
                            >
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>