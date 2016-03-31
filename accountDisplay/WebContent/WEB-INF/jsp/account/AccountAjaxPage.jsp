<%@ page language="java" contentType="text/html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:choose>
	<c:when test="${opt eq 'RunQuery' || opt eq 'Paginate' }">
	<input id="totalRecords" type="hidden" value="${accountListSize}" />
		<div id="table_wrapper" style="margin-right:17px;">
		  	<table class="table table-bordered table-fixed aqua-table tele-icons" style="margin-bottom:0px;">							
			  	<thead>
			  		<th class="dycol1"></th>  	
			    	<th class="dycol2">Name</th>
			    	<th class="dycol3">Market</th>
			    	<th class="dycol4">Phone</th>
			    	<th class="dycol5">Record Type</th>
		    	</thead>
		  	</table>
	  	</div>
		<div id="tbody" style="overflow-y:scroll;overflow-x:hidden;">
			<table class="table table-bordered table-fixed aqua-table tele-icons" id="this-table">
				<c:if test="${accountListSize==0}">
					<tr>
						<td colspan="5">No records found for the search.</td>
					</tr>
				</c:if>
				<c:forEach var="account" items="${accountList}">
					<tr>
					<td class="dycol1"><input type="checkbox" name="s_account" class="mr10 pull-left" value="${account.id}" />
						<i class="icon icon-exlametry mt4 ml20" ></i></td>
						<td class="dycol2">${account.name}</td>
						<td class="dycol3">${account.market}</td>
						<td class="dycol4">${account.phone}</td>
						<td class="dycol5">${account.recordtype}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</c:when>
	<c:when test="${opt eq 'Lookup'}">
		<div class="pad10">
			<div class="form-group">
				<label class="col-sm-4 control-label">Search String</label>
				<div class="col-sm-6">
					<input type="text" name="lookupName" id="lookupName" value="${lookupName}">
				</div>
				<button class="btn btn-blue btn-sm btn-xs" onclick="loadLookupData('${type}',$('#lookupName').val())">Go</button>
			</div>
		</div>
		<table class="table table-bordered aqua-table tele-icons" id="this-table">
			<thead>
				<tr>
					<th>Name</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="lookup" items="${lookupList}">
					<tr>
						<td style="text-align:left"><a href="javascript:void(0)" onclick="selectFilter('${type}','${lookup.id}','${lookup.name}')">${lookup.name}</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:when>
	<c:when test="${opt eq 'ReviewGoal' || opt eq 'PaginateReview'}">
	<form class="form-horizontal" id="strategicForm" method="post" >
		<table class="table" style="margin-bottom:0px;">
			<tbody>
				<tr>
					<td style="width:400px;padding:0px;">
						<div class="form-group">
							<label class="col-sm-5 control-label">Stategic Initiative Type</label>
							<div class="col-sm-7">
								<select class="input-sm"  name="stategicInitiativeType" style="height:24px;padding: 1px 3px;" >
									<option value="">--Select Record Type--</option>
									<option value="${selectedOptionValue}">Strategic Goal</option>
								</select>
							</div>
						</div>
					</td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
		</table>
		<table class="table" style="margin-bottom:0px;">
			<tbody>
				<tr>
					<td>
						<div class="form-group" style="margin-bottom:0px;">
							<label class="col-sm-5 control-label">Strategic Goal Name</label>
							<div class="col-sm-7" style="padding-top:7px;">
								${parentProgramName}
							</div>
						</div>
						<div class="form-group" style="margin-bottom:0px;">
							<label class="col-sm-5 control-label" >Goal Note</label>
							<div class="col-sm-7" style="padding-top:7px;">
								${goalNote}
							</div>
						</div>
						<div class="form-group" style="margin-bottom:0px;">
							<label class="col-sm-5 control-label">Goal Type</label>
							<div class="col-sm-7" style="padding-top:7px;">
								${goalType}
							</div>
						</div>
					</td>
					<td>
						<div class="form-group" style="margin-bottom:0px;">
							<label class="col-sm-5 control-label">Parent Program</label>
							<div class="col-sm-7" style="padding-top:7px;">
								${parentProgram}
							</div>
						</div>
						<div class="form-group" style="margin-bottom:0px;">
							<label class="col-sm-5 control-label">Goal Status</label>
							<div class="col-sm-7" style="padding-top:7px;">
								${goalStatus}
							</div>
						</div>
						<div class="form-group" style="margin-bottom:0px;">
							<label class="col-sm-5 control-label">Completed Date</label>
							<div class="col-sm-7" style="padding-top:7px;">
								${completedDate}
							</div>
						</div>
					</td>
					
				</tr>
			</tbody>
		</table>
	</form>
		<div class="darkblue-nav">
			<div class="darkpad">
				<button class="btn btn-xs btn-aquagrey mr5" onclick="addMoreAccounts();">Add More Accounts</button>
				<button class="btn btn-xs btn-aquagrey mr5" onclick="createGoal();">Create Goal</button>
				<input id="totalRecordsReview" type="hidden" value="${ReviewListSize}" />
				<div class="clearfix"></div>
			</div>
		</div>
		<div id="table_wrapper1" style="margin-right:17px;">
		  	<table class="table table-bordered table-fixed aqua-table tele-icons" style="margin-bottom:0px;">							
			  	<thead>  	
			    	<th class="dycol2">Name</th>
			    	<th class="dycol3">Market</th>
			    	<th class="dycol4">Phone</th>
			    	<th class="dycol5">Record Type</th>
		    	</thead>
		  	</table>
	  	</div>
		<div id="tbody1" style="overflow-y:scroll;overflow-x:hidden;">
			<table class="table table-bordered table-fixed aqua-table tele-icons" id="this-table1">
				<c:if test="${ReviewListSize==0}">
					<tr>
						<td colspan="5">No records found for the search.</td>
					</tr>
				</c:if>
				<c:forEach var="account" items="${ReviewList}">
					<tr>
						<td class="dycol2"><input type="hidden" name="s_account1"  class="mr10 pull-left"  value="${account.id}" />${account.name}</td>
						<td class="dycol3">${account.market}</td>
						<td class="dycol4">${account.phone}</td>
						<td class="dycol5">${account.recordtype}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</c:when>
</c:choose>
