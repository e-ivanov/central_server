function Chart(config) {
    new Morris.Line({
        // ID of the element in which to draw the chart.
        element: config.element,
        // Chart data records -- each entry in this array corresponds to a point on
        // the chart.
        data: config.items,
        // The name of the data record attribute that contains x-values.
        xkey: config.xkey,
        // A list of names of data record attributes that contain y-values.
        ykeys: config.ykeys,
        // Labels for the ykeys -- will be displayed when you hover over the
        // chart.
        labels: config.labels
    });
}


