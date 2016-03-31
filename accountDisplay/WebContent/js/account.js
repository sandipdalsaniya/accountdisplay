var myControl = {
	create : function(tp_inst, obj, unit, val, min, max, step) {
		$(
				'<input class="ui-timepicker-input" value="' + val
						+ '" style="width:50%">').appendTo(obj).spinner({
			min : min,
			max : max,
			step : step,
			change : function(e, ui) { // key events
				// don't call if api was used and not key press
				if (e.originalEvent !== undefined)
					tp_inst._onTimeChange();
				tp_inst._onSelectHandler();
			},
			spin : function(e, ui) { // spin events
				tp_inst.control.value(tp_inst, obj, unit, ui.value);
				tp_inst._onTimeChange();
				tp_inst._onSelectHandler();
			}
		});
		return obj;
	},
	options : function(tp_inst, obj, unit, opts, val) {
		if (typeof (opts) == 'string' && val !== undefined)
			return obj.find('.ui-timepicker-input').spinner(opts, val);
		return obj.find('.ui-timepicker-input').spinner(opts);
	},
	value : function(tp_inst, obj, unit, val) {
		if (val !== undefined)
			return obj.find('.ui-timepicker-input').spinner('value', val);
		return obj.find('.ui-timepicker-input').spinner('value');
	}
};

$("#from").datetimepicker({
	controlType : myControl,
	defaultDate : "+1w",
	changeMonth : true,
	dateFormat : 'd M yy',
	timeFormat : "hh:mm TT",
	numberOfMonths : 1,
	onClose : function(selectedDate) {
		$("#to").datetimepicker("option", "minDate", selectedDate);
	}
});
$("#to").datetimepicker({
	controlType : myControl,
	defaultDate : "+1w",
	changeMonth : true,
	dateFormat : 'd M yy',
	timeFormat : "hh:mm TT",
	numberOfMonths : 1,
	onClose : function(selectedDate) {
		$("#from").datetimepicker("option", "maxDate", selectedDate);
	}
});

$(".icon-inputcalender").click(
		function() {
			$(this).parent().parent().find('input[type="text"]')
					.datetimepicker("show");
		});
var myApp;
myApp = myApp || (function() {
	var pleaseWaitDiv = $('#pleaseWaitDialog');
	return {
		showPleaseWait : function() {
			pleaseWaitDiv.modal();
		},
		hidePleaseWait : function() {
			pleaseWaitDiv.modal('hide');
		},

	};
})();
var contextPath = $("#contextPath").val();
function loadAccounts(type) {
	var pageSize = $("#pageSize").val();
	$("#noOfRecords").val(pageSize);
	var formData = $("#accountForm").serialize();
	myApp.showPleaseWait();
	$.get(contextPath + "/AccountServlet?opt=" + type + "&" + formData,
	function(data) {
		$("#searchResponse").html(data);
		myApp.hidePleaseWait();
		setHeightWidth();
	});
}

function runQuery() {
	loadAccounts('RunQuery');
}
function currentPage() {
	$("#pageNo").val("1");
	loadAccounts('Paginate');
}

function currentPageReview() {
	$("#pageNo").val("1");
	loadReviewAccounts('PaginateReview');
}
function nextPage() {
	var totalRecords=parseInt($("#totalRecords").val()); //15
	var noOfRecords = parseInt($("#noOfRecords").val()); //10
	var pageNo = parseInt($("#pageNo").val()); // 2
	if((noOfRecords*(pageNo)+1)<totalRecords){
		$("#pageNo").val(pageNo + 1);
		loadAccounts('Paginate');	
	}else{
		//$("#btnNext").prop("disabled", true );
		//$("#btnPrev").prop("disabled", false);
	}
}
function nextPageReview() {
	var totalRecords=parseInt($("#totalRecordsReview").val()); //15
	var noOfRecords = parseInt($("#noOfRecordsReview").val()); //10
	var pageNo = parseInt($("#pageNoReview").val()); // 2
	if((noOfRecords*(pageNo)+1)<totalRecords){
		$("#pageNoReview").val(pageNo + 1);
		loadReviewAccounts('PaginateReview');	
	}else{
		//$("#btnNext").prop("disabled", true );
		//$("#btnPrev").prop("disabled", false);
	}
}

function prevPage() {
	var pageNo = $("#pageNo").val();
	if (pageNo > 1) {
		$("#pageNo").val(parseInt(pageNo) - 1);
		loadAccounts('Paginate');
	}else{
		//$("#btnNext").prop("disabled", false );
		//$("#btnPrev").prop("disabled", true);
	}
}
function prevPageReview() {
	var pageNo = $("#pageNoReview").val();
	if (pageNo > 1) {
		$("#pageNoReview").val(parseInt(pageNo) - 1);
		loadReviewAccounts('PaginateReview');
	}else{
		//$("#btnNext").prop("disabled", false );
		//$("#btnPrev").prop("disabled", true);
	}
}

function loadLookupData(type,lookupName){
	$.get(contextPath + "/AccountServlet?opt=Lookup&type=" + type + "&lookupName="+lookupName,
	function(data) {
		$("#myModalLabel").html(type+" Lookup");
		$("#lookupContainer").html(data);
		$("#lookupPopup").modal();
	});
}

function resetSearch(){
	$("#accountForm")[0].reset();
	runQuery();
}

function selectFilter(type,id,name){
	if(type=='Brand'){
		$("#brandName").val(name);
		$("#brandId").val(id);
	}else if(type=='Product'){
		$("#productName").val(name);
		$("#productId").val(id);
	}else if(type=='PrimaryChannel'){
		$("#primaryChannel").val(name);
		$("#primaryChannelId").val(id);
	}else if(type=='Account'){
		$("#parentAccount").val(name);
		$("#parentAccountId").val(id);
	}
	$("#lookupPopup").modal('hide');
}

function addAllAccounts(){
	myApp.showPleaseWait();
	$.get(contextPath + "/AccountServlet?opt=AddAllAcccounts",
	function(data) {
		myApp.hidePleaseWait();
		notifyMe("All account added successfully.",'info');
	});
}

function addSelectedAccounts(){
	var arrAccounts=$('input[name=s_account]:checked').map(function(){
        return $(this).val();
    });
	if(arrAccounts.length==0){
		notifyMe("Please select atleast one account.",'warning');
		return false;
	}
	var selectedAccounts = arrAccounts.get();
	myApp.showPleaseWait();
	$.ajax({
	  url: contextPath + "/AccountServlet?opt=AddSelectedAcccounts",
	  data:{'arrayData':selectedAccounts},
	  type: 'post',
	  success: function(data) {
		  myApp.hidePleaseWait();
		  notifyMe('Selected account added successfully.','info');
	  }
	});
}

function reviewGoal(){
	$("#reviewPopup").modal();
	loadReviewAccounts('ReviewGoal');
}

function loadReviewAccounts(type){
	myApp.showPleaseWait();
	var formFields = $("#strategicForm").serialize();
	
	if(type=='ReviewGoal'){
		$("#noOfRecordsReview").val($("#pageSizeReview").val());
		$("#pageNoReview").val("1");
	}
	var noOfRecordsReview = $("#noOfRecordsReview").val();
	var pageNoReview = $("#pageNoReview").val();
	$.get(contextPath + "/AccountServlet?opt="+type+"&noOfRecordsReview="+noOfRecordsReview+"&pageNoReview="+pageNoReview+"&"+formFields,
	function(data) {
		myApp.hidePleaseWait();
		$("#reviewContainer").html(data);
		var th = window.screen.availHeight-250;	
		$("#tbody1").css('height',th+'px');
	});
}
function addMoreAccounts(){
	$("#reviewPopup").modal('hide');
}

function clearFilter(type){
	if(type=='Brand'){
		$("#brandName").val("");
		$("#brandId").val("");
	}else if(type=='Product'){
		$("#productName").val("");
		$("#productId").val("");
	}else if(type=='PrimaryChannel'){
		$("#primaryChannel").val("");
		$("#primaryChannelId").val("");
	}else if(type=='Account'){
		$("#parentAccount").val("");
		$("#parentAccountId").val("");
	}
}

function createGoal(){
	var formFields = $("#strategicForm").serialize();
	$.ajax({
	  url: contextPath + "/AccountServlet?opt=CreateGoal",
	  data:formFields,
	  type: 'post',
	  success: function(data) {
		  myApp.hidePleaseWait();
		  notifyMe('Goal created successfully.','info');
	  }
	});
	//notifyMe('Under development','info',1050);
}