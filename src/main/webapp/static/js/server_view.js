function ServerView(containerId) {

    this.containderId = containerId,
        this.cpuView = {},
        this.memoryView = {},
        this.model = {},
        this.cpuViewSelector = "",
        this.memoryViewSelector = "",
        this.systemUpTimeSelector = "",
        this.initialize = function () {
            this.memoryView = new GraphView();
            this.memoryView.initialize();

            this.cpuView = new GraphView();
            this.cpuView.initialize();

            this.cpuView.selector = 'cpu_plot';
            this.memoryView.selector = 'memory_plot';
            this.systemUpTimeSelector = 'system_uptime_id';
            this.cpuView.setUpPlot();
            this.memoryView.setUpPlot();
        },
        this.showCpu = function () {

            console.log("on change cpu called")
            this.cpuView.y = this.model.cpuData.usage;
            this.cpuView.render();
        },
        this.showMemory = function () {
            this.memoryView.y = this.model.memoryData.usage_percent;
            this.memoryView.render();
            console.log("on change memory called")
        },
        this.showDiskData = function () {
            var view = new graphView();
        },
        this.showNetworkData = function () {
            var view = new graphView();
        },
        this.showSystemUptime = function () {
            console.log(this.systemUpTimeSelector + ' p')
            $("#" + this.systemUpTimeSelector + ' p').text(this.model.system_uptime)
        },
        this.showProcesses = function () {
            //var view = new graphView();
            console.log("processes changed")
        },
        this.selector = {},
        this.update = function () {
            this.showCpu();
            this.showMemory();
            this.showSystemUptime();

        }
}
