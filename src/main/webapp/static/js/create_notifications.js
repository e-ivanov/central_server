var newMailChannelFormTpl = '<div class="panel"> <a data-toggle="collapse" data-parent="#emailAccordion" href="#collapseEmail{{id}}">{{emailAddress}} </a> <div id="#collapseEmail{{id}}" class="panel-collapse collapse"> <div class="panel-body"><form action="{{url}}" method="POST" class="form-horizontal" > <input type="hidden" name="id" value="{{id}}" /> <div class="row"> <div class="form-group col-md-12"> <label class="col-md-3 control-lable" for="name">Name:</label> <div class="col-md-7"> <input type="text" name="channelName" value="{{channelName}}" id="name" class="form-control input-sm"/> <div class="has-error"> </div> </div> </div> </div> <div class="row"> <div class="form-group col-md-12"> <label class="col-md-3 control-lable" for="email_address">Email address:</label> <div class="col-md-7"> <input type="text" name="emailAddress" value="{{emailAddress}}" id="email_address" class="form-control input-sm"/> <div class="has-error"> </div> </div> </div> </div> <button type="submit" class="btn btn-info" value="Send">Запази</button><button type="submit" class="btn btn-danger" value="Delete">Изтрий</button> </form></div></div></div>';
var token;
var header;
$(document).ready(function () {
    $("#submit_new_email_channel").click(function (evt) {
        evt.preventDefault();
        var formData = {
            "channelName": $("#new_channel_name").val(),
            "emailAddress": $("#new_email_address").val()
        };
        
        token = $("meta[name='_csrf']").attr("content");
        header = $("meta[name='_csrf_header']").attr("content");
        var formUrl = ctx+"/notificationChannel/create/email"
        submitNewElementForm(formData, formUrl, newMailChannelFormTpl, $("#new_main_model_form_container"), $("#mail_list_container"))
        return false;
    });
});

$(document).on("click", "#mail_list_container form.form-horizontal button", function(evt){
        evt.preventDefault();
        var action = $(this).val();
        var formUpdateUrl = ctx+"/notificationChannel/update";
        var formDeleteUrl = ctx+"/notificationChannel/delete";
        var targetForm = $(this).parents('form.form-horizontal:first');
        var formData = {
            "id" : $("input:hidden[name='id']", $(targetForm)).val(),
            "channelName": $("input:text[name='channelName']", $(targetForm)).val(),
            "emailAddress": $("input:text[name='emailAddress']", $(targetForm)).val()
        };
 
        if(action == "Send"){
            updateExistingElement(formUpdateUrl, formData, null);
        }else if(action == "Delete"){
            updateExistingElement(formDeleteUrl, formData, $(targetForm).parents("div.panel:first"));
        }

})
    
    
function updateExistingElement(formUrl, formData, parentToDelete){
    $.ajax({url: formUrl, type: 'post', data: formData, datatype: 'json', function (response) {
            if (response.status == 'FAIL') {
                alert("Fail")
            } else {
                if(parentToDelete){
                parentToDelete.remove();
            }
                alert("Success")
            }
        }, headers: {header: token} });
}


function submitNewElementForm(formData, formUrl, formTemplate, modalId, containerId){
        $.ajax({url: formUrl, type: 'post', data: formData, datatype: 'json', function (response) {
            if (response.status == 'FAIL') {
                alert("Fail")
            } else {
                modalId.modal("hide");
                alert("Success")
                var data = response.payload;
                response.payload.url = formUrl;
                containerId.append(Mustache.render(newMailChannelFormTpl, response.payload));
            }
        }, headers: {header: token}});
}

