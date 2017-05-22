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
                <div class="col-md-6">
                    <div id="live_data">
                        <h2>Процесор</h2>
                        <div id="cpu_plot">

                        </div>
                        <h2>Памет</h2>
                        <div id="memory_plot"></div>
                    </div>
                    <h2>Процесор</h2>
                    <div id="cpuArchiveChart"></div>
                    <h2>Памет</h2>
                    <div id="memoryArchiveChart"></div>
                    <h2>Мрежова активност</h2>
                    <div id="networkArchiveChart"></div>
                </div>
                <div class="col-md-4">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                Нотификации
                            </h3>
                        </div>
                        <div class="panel-body">
                            Panel content
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="<c:url value="/static/js/jquery.min.js" />"></script>
        <script src="<c:url value="/static/js/bootstrap.min.js" />"></script>
        <!--<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.8.3/underscore-min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/backbone.js/1.2.3/backbone-min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jqPlot/1.0.8/jquery.jqplot.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jqPlot/1.0.8/plugins/jqplot.categoryAxisRenderer.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jqPlot/1.0.8/plugins/jqplot.dateAxisRenderer.min.js"></script>
        <script src="http://cdnjs.cloudflare.com/ajax/libs/sockjs-client/0.3.4/sockjs.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.min.js"></script>
        <script src="<c:url value="/static/js/server_view.js" />"></script>
        <script src="<c:url value="/static/js/server_model.js" />"></script>
        <script src="<c:url value="/static/js/graphView.js"/>"></script>
        <script src="<c:url value="/static/js/chart.js" />"></script>
        <script src="<c:url value="/static/js/scripts.js" />"></script>
        <script>
            var cpuItems = [];
            var memoryItems = [];
            var networkItems = [];
            <c:forEach var="item" items="${perf_data}">
            cpuItems.push({year: "${item.timestamp}", value: ${item.cpuData.usage}});
            memoryItems.push({year: "${item.timestamp}", value: ${item.memoryData.usagePercent}})
            networkItems.push({year: "${item.timestamp}", bytes_recv: ${item.networkData.bytesRecv}, bytes_sent: ${item.networkData.bytesSent}})
            </c:forEach>
            var cpuChartConfig = {element: "cpuArchiveChart", items: cpuItems, xkey: "year", ykeys: ['value'], labels: ['Стойност']};
            var memoryChartConfig = {element: "memoryArchiveChart", items: memoryItems, xkey: "year", ykeys: ['value'], labels: ['Стойност']};
            var networkChartConfig = {element: "networkArchiveChart", items: networkItems, xkey: "year", ykeys: ['bytes_recv', 'bytes_sent'], labels: ['Bytes received', 'Bytes sent']}
            var cpuChart;
            var memoryChart;
            var networkChart;
            var server_model = new serverModel();
            var server_view = new ServerView("live_data"); // server_view.model = server_model;

            $(document).ready(function () {
                cpuChart = new Chart(cpuChartConfig);
                memoryChart = new Chart(memoryChartConfig);
                networkChart = new Chart(networkChartConfig)
                server_model.view = server_view;
                server_view.model = server_model;
                server_view.initialize();


                var ws = new SockJS('http://127.0.0.1:15674/stomp');
                var client = Stomp.over(ws);
                var on_connect = function () {
                    console.log('connected successfully')
                    var headers = {durable: false, "auto-delete": true, exclusive: true};
                    client.subscribe('/exchange/server_data/server_${server_id}', function (d) {
                        doUpdate(JSON.parse(d.body));
                    }, headers);

                };
                var on_error = function () {
                    console.log('error');
                };


                console.log("Trying to connect")
                client.connect('admin', 'admin', on_connect, on_error, '/');
            });

            function doUpdate(serverData) {
                server_model.cpuData = serverData.cpuData;
                server_model.memoryData = serverData.memoryData;
                server_model.view.update();
            }
        </script>
    </body>
</html>
