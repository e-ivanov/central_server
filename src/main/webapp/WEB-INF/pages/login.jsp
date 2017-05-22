<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="fragments/header.jspf" %>
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
            <div class="col-md-2"></div>
            <div class="row">
                <div class="col-md-8">
                    <form name='loginForm'
                      action="<c:url value='login.html' />" method='POST'>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="name">Потребител:</label>
                                <div class="col-md-7">
                                    <input type='text' class="form-control input-sm" name='username' value=''>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="name">Парола:</label>
                                <div class="col-md-7">
                                    <input type='password' class="form-control input-sm" name='password' />
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <div class="col-md-6"></div>
                                <div class="col-md-5">
                                     <button type="submit" class="btn btn-info" value="Send">Вход</button>
                                </div>
                            </div>
                       
                        </div>
                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}" />

                </form>
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