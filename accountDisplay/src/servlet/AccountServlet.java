package servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pojo.InputAccount;
import pojo.StrategicGoal;
import pojo.WrapAccount;
import pojo.WrapLookup;
import service.AccountServices;
import utils.CacheManager;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AccountServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doProcess(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InputAccount inputAccount = new InputAccount();
		List<WrapAccount> lstAccounts = new ArrayList<WrapAccount>();
		String pageName = "WEB-INF/jsp/account/AccountAjaxPage.jsp";
		//HttpSession session = request.getSession();
		CacheManager cacheManager = CacheManager.getInstance();
		if(request.getParameter("opt")!=null && request.getParameter("opt").equals("Dashboard")){
			// Get From Database
			inputAccount=setInputAccount(request);
			lstAccounts = new AccountServices().getWrapAccounts(inputAccount);
			int end = Integer.parseInt(request.getParameter("noOfRecords"));
			cacheManager.setReviewAccounts(new HashMap<String,WrapAccount>());
			if(lstAccounts.size()<end){
				end=lstAccounts.size();
			}
			
			request.setAttribute("accountListSize", lstAccounts.size());
			List<WrapAccount> pageAccounts = lstAccounts.subList(0, end);
			cacheManager.setAccounts(lstAccounts);
			request.setAttribute("accountList", pageAccounts);
			request.setAttribute("parentProgramName",request.getParameter("parentProgramName"));
			request.setAttribute("name",request.getParameter("name"));
			request.setAttribute("parentProgramId",request.getParameter("parentProgramId"));
			request.setAttribute("selectedOptionValue",request.getParameter("selectedOptionValue"));
			Date now =new Date();
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			if(request.getParameter("startDate")!=null && !request.getParameter("startDate").equals("")){
				request.setAttribute("startDate",request.getParameter("startDate"));	
			}else{
				request.setAttribute("startDate",df.format(now));
			}
			//request.setAttribute("selectedOptionValue",request.getParameter("selectedOptionValue"));
			request.setAttribute("goalStatus","Not Started");
			pageName = "WEB-INF/jsp/account/Accounts.jsp";
		}else if(request.getParameter("opt")!=null && request.getParameter("opt").equals("RunQuery")){
			// Get From Database
			inputAccount=setInputAccount(request);
			System.out.println("inputAccount="+inputAccount);
			cacheManager.setReviewAccounts(new HashMap<String,WrapAccount>());
			lstAccounts = new AccountServices().getWrapAccounts(inputAccount);
			int end = Integer.parseInt(request.getParameter("noOfRecords"));
			if(lstAccounts.size()<end){
				end=lstAccounts.size();
			}
			request.setAttribute("accountListSize", lstAccounts.size());
			List<WrapAccount> pageAccounts = lstAccounts.subList(0,end);
			pageName = "WEB-INF/jsp/account/AccountAjaxPage.jsp";
			cacheManager.setSessionAccounts(lstAccounts);
			request.setAttribute("accountList", pageAccounts);
		}else if(request.getParameter("opt")!=null && request.getParameter("opt").equals("Paginate")){
			// Get From Cached
			lstAccounts =cacheManager.getAccounts();
			System.out.println("lstAccounts="+lstAccounts.size());
			int noOfRecords = Integer.parseInt(request.getParameter("noOfRecords"));
			int pageNo = Integer.parseInt(request.getParameter("pageNo"));
			System.out.println("noOfRecords="+noOfRecords);
			System.out.println("pageNo="+pageNo);
			int start=(noOfRecords*(pageNo-1));
			int end=start+noOfRecords;
			System.out.println("start="+start);
			System.out.println("end="+end);
			if(lstAccounts.size()<end){
				end=lstAccounts.size();
			}
			request.setAttribute("accountListSize", lstAccounts.size());
			List<WrapAccount> pageAccounts = lstAccounts.subList(start, end);
			request.setAttribute("accountList", pageAccounts);
			pageName = "WEB-INF/jsp/account/AccountAjaxPage.jsp";
		}else if(request.getParameter("opt")!=null && request.getParameter("opt").equals("PaginateReview")){
			// Get From Cached
			Map<String, WrapAccount> resultsMap = cacheManager.getReviewAccounts();
			List<WrapAccount> lstRAccounts = new ArrayList(resultsMap.values());
			
			int noOfRecords = Integer.parseInt(request.getParameter("noOfRecordsReview"));
			int pageNo = Integer.parseInt(request.getParameter("pageNoReview"));
			int start=(noOfRecords*(pageNo-1));
			int end=start+noOfRecords;
			if(lstRAccounts.size()<end){
				end=lstRAccounts.size();
			}
			System.out.println("start="+start);
			System.out.println("End="+end);
			request.setAttribute("ReviewListSize", lstRAccounts.size());
			List<WrapAccount> pageAccounts = lstRAccounts.subList(start, end);
			request.setAttribute("ReviewList", pageAccounts);
			
			request.setAttribute("opt", "ReviewGoal");
			pageName = "WEB-INF/jsp/account/AccountAjaxPage.jsp";
		}else if(request.getParameter("opt")!=null && request.getParameter("opt").equals("Lookup")){
			String type=request.getParameter("type");
			String lookupName=request.getParameter("lookupName");
			pageName = "WEB-INF/jsp/account/AccountAjaxPage.jsp";
			List<WrapLookup> lstLookupData = new AccountServices().getWrapLookup(type, lookupName);
			System.out.println(lstLookupData);
			request.setAttribute("lookupList",lstLookupData);
			request.setAttribute("type",type);
			request.setAttribute("opt",request.getParameter("opt"));
			request.setAttribute("lookupName",lookupName);
		}else if(request.getParameter("opt")!=null && request.getParameter("opt").equals("AddAllAcccounts")){
			lstAccounts = cacheManager.getSessionAccounts();
			Map<String, WrapAccount> resultsMap = cacheManager.getReviewAccounts();
			for (WrapAccount acc: lstAccounts) {
			    resultsMap.put(acc.getId(), acc);
			}
			cacheManager.setReviewAccounts(resultsMap);
			pageName = "WEB-INF/jsp/account/AccountAjaxPage.jsp";
		}else if(request.getParameter("opt")!=null && request.getParameter("opt").equals("AddSelectedAcccounts")){
			inputAccount=setInputAccount(request);
			lstAccounts = new AccountServices().getWrapAccounts(inputAccount);
			Map<String, WrapAccount> resultsMap = cacheManager.getReviewAccounts();
			for (WrapAccount acc: lstAccounts) {
			    resultsMap.put(acc.getId(), acc);
			}
			cacheManager.setReviewAccounts(resultsMap);
			pageName = "WEB-INF/jsp/account/AccountAjaxPage.jsp";
			
		}else if(request.getParameter("opt")!=null && request.getParameter("opt").equals("ReviewGoal")){
			Map<String, WrapAccount> resultsMap = cacheManager.getReviewAccounts();
			List<WrapAccount> lstRAccounts= new ArrayList(resultsMap.values());
			int end=Integer.parseInt(request.getParameter("noOfRecordsReview"));
			if(lstRAccounts.size()<end){
				end=lstRAccounts.size();
			}
			request.setAttribute("ReviewListSize",lstRAccounts.size());
			request.setAttribute("ReviewList", lstRAccounts.subList(0,end));
			request.setAttribute("opt", "ReviewGoal");
			pageName = "WEB-INF/jsp/account/AccountAjaxPage.jsp";
			request.setAttribute("parentProgramName",request.getParameter("parentProgramName"));
			request.setAttribute("parentProgramId",request.getParameter("parentProgramId"));
			request.setAttribute("selectedOptionValue",request.getParameter("selectedOptionValue"));
			request.setAttribute("goalStatus","Not Started");
		}else if(request.getParameter("opt")!=null && request.getParameter("opt").equals("CreateGoal")){
			StrategicGoal goal = new StrategicGoal();
			goal.setName(request.getParameter("parentProgramName"));
			goal.setParentProgramName(request.getParameter("parentProgramName"));
			goal.setProgramc(request.getParameter("parentProgramId"));
			goal.setStartDate(Calendar.getInstance());
			goal.setGoalStatus(request.getParameter("goalStatus"));
			goal.setRecordTypeId(request.getParameter("selectedOptionValue"));
			Map<String, WrapAccount> resultsMap = cacheManager.getReviewAccounts();
			List<WrapAccount> lstRAccounts= new ArrayList(resultsMap.values());
			System.out.println("lstRAccounts="+lstRAccounts);
			new AccountServices().createGoal(lstRAccounts,goal);
		}
		request.setAttribute("opt",request.getParameter("opt"));
		RequestDispatcher dispatcher = request.getRequestDispatcher(pageName);
		dispatcher.forward(request, response);		
	}
	
	private InputAccount setInputAccount(HttpServletRequest request){
		InputAccount inputAccount = new InputAccount();
		String[] arrayData=request.getParameterValues("arrayData[]");
		if(arrayData!=null){
			StringBuilder sb = new StringBuilder();
			for (String n : arrayData) { 
			    if (sb.length() > 0) sb.append(',');
			    sb.append("'").append(n).append("'");
			}
			if(sb.length()>0){
				inputAccount.setId(sb.toString());
			}
		}
		inputAccount.setAccountName(request.getParameter("accountName"));
		inputAccount.setMarket(request.getParameter("market"));
		inputAccount.setParentAccountId(request.getParameter("parentAccountId"));
		inputAccount.setBrandId(request.getParameter("brandId"));
		inputAccount.setProductId(request.getParameter("productId"));
		if(request.getParameter("type")!=null && !request.getParameter("type").equals("-1")){
			inputAccount.setType(request.getParameter("type"));			
		}
		inputAccount.setPrimaryChannelId(request.getParameter("primaryChannelId"));
		if(request.getParameter("accountStatus")!=null && !request.getParameter("accountStatus").equals("-1")){			
			inputAccount.setAccountStatus(request.getParameter("accountStatus"));
		}
		if(request.getParameter("brandStatus")!=null && !request.getParameter("brandStatus").equals("-1")){
			inputAccount.setBrandStatus(request.getParameter("brandStatus"));
		}
		return inputAccount;
	}
}

