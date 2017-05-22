var serverModel = Backbone.Model.extend({
    
})

function serverModel(view) {
    this.view =view;
    this.memoryData = {};
    this.cpuData = {};
    this.diskData = {};
    this.networkData = {}
//    system_uptime: {},
//    processList: {}
}