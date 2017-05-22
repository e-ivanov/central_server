function GraphView() {


    this.t = 1000,
        this.x = new Date().getTime(), // current time
        this.n = 5,
        this.y = {},
        this.data = [],
        this.plot = {

        },
        this.selector = {},
        this.initialize = function () {
            for (i = 0; i < this.n; i++) {
                this.data.push([this.x - (this.n - 1 - i) * this.t, 0]);
            }

            this.options = {
                title: Math.floor((Math.random() * 10) + 1),
                axesDefaults: {
                    tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                    tickOptions: {
                        angle: -30,
                        fontSize: '9pt'
                    }
                },
                axes: {
                    xaxis: {
                        numberTicks: 4,
                        renderer: $.jqplot.DateAxisRenderer,
                        tickOptions: {
                            formatString: '%H:%M:%S'
                        },
                        min: this.data[0][0],
                        max: this.data[this.data.length - 1][0]
                    },
                    yaxis: {
                        min: 0,
                        max: 100,
                        numberTicks: 10,
                        tickOptions: {
                            formatString: '%.1f'
                        }
                    }
                },
                seriesDefaults: {
                    rendererOptions: {
                        smooth: true
                    }
                }
            }




        },
        this.setUpPlot = function () {
            console.log(this.selector)
            this.plot = $.jqplot(this.selector, [this.data], this.options);
        },
        this.render = function () {

            if (this.data.length > this.n - 1) {
                this.data.shift();
            }
            var y = this.y;
            console.log(y)
            var x = new Date().getTime();
            this.data.push([x, y]);
            if (this.plot) {
                this.plot.destroy();
            }
            this.plot.series[0].data = this.data;
            this.options.axes.xaxis.min = this.data[0][0];
            this.options.axes.xaxis.max = this.data[this.data.length - 1][0];
            this.plot = $.jqplot(this.selector, [this.data], this.options);
        },
        this.options = {

        }


}