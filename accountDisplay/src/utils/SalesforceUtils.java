package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pojo.InputAccount;
import pojo.StrategicGoal;
import pojo.WrapAccount;
import pojo.WrapLookup;

import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.SaveResult;
import com.sforce.soap.enterprise.sobject.Account;
import com.sforce.soap.enterprise.sobject.Product2;
import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.soap.enterprise.sobject.Test89__Brand__c;
import com.sforce.soap.enterprise.sobject.Test89__Channel__c;
import com.sforce.soap.enterprise.sobject.Test89__Mass_Assign_Batch__c;
import com.sforce.soap.enterprise.sobject.Test89__Product__c;
import com.sforce.soap.enterprise.sobject.Test89__Strategic_Goal__c;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

public class SalesforceUtils {
	EnterpriseConnection con;
	public static void main(String[] args) {
		try {
			SalesforceUtils objSFDCConnect = new SalesforceUtils();
			
			//OpenConn("https://login.salesforce.com/services/Soap/c/35.0/", "hiteshpatel@salesforce.com", "hello123", "iMNsUj66NAkOGrt2RkRovfyB", "");
			objSFDCConnect.OpenConn();
			objSFDCConnect.searchAccounts(new InputAccount());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void OpenConn() throws ConnectionException{
		try {
			CustomProperties customProperties = CustomProperties.getInstance();
			customProperties.getValueByKeyName("salesforce.url");
			ConnectorConfig config = new ConnectorConfig();
			config.setUsername(customProperties.getValueByKeyName("salesforce.username"));
			config.setPassword(customProperties.getValueByKeyName("salesforce.password")+customProperties.getValueByKeyName("salesforce.token"));
			config.setAuthEndpoint(customProperties.getValueByKeyName("salesforce.url"));
			String logfile=customProperties.getValueByKeyName("salesforce.logfile");
			System.out.println(customProperties.getValueByKeyName("salesforce.username"));
			System.out.println(customProperties.getValueByKeyName("salesforce.password"));
			System.out.println(customProperties.getValueByKeyName("salesforce.token"));
			System.out.println(customProperties.getValueByKeyName("salesforce.url"));
			if (logfile.length() > 0) {
				config.setTraceFile(logfile);
				config.setTraceMessage(true);
				config.setPrettyPrintXml(true);
			}
			con = new EnterpriseConnection(config);
			config.setServiceEndpoint(config.getServiceEndpoint());
			config.setSessionId(config.getSessionId());
		}
		catch (Exception e) {
			System.out.println("Eror " + e.toString());
		}

	}
	/**
	 * Brand Lookup
	 * @param brandName
	 * @return
	 */
	public List<WrapLookup> getBrands(String brandName){
		List<WrapLookup> lstBrands = new ArrayList<>();
		try{
			OpenConn();
		StringBuilder query =new StringBuilder();
		query.append("select id, name from test89__Brand__c");
		if(brandName!=null && !brandName.equals("")){
			query.append(" where name='"+brandName+"'");
		}else{
			query.append(" limit 10");
		}
		System.out.println("query="+query.toString());
		QueryResult sr = con.query(query.toString());
		SObject[] records = sr.getRecords();
		if (records != null && records.length > 0) {
			for (SObject brd : records) {
				Test89__Brand__c tbrand = (Test89__Brand__c)brd;
				WrapLookup lookup = new WrapLookup();
				lookup.setId(tbrand.getId());
				lookup.setName(tbrand.getName());
				lstBrands.add(lookup);
			}	
		}
		} catch (ConnectionException ce) {
			ce.printStackTrace();
			System.out.print("exception");
		}
		return lstBrands;
	}
	/**
	 * Product Lookup
	 * @param productName
	 * @return
	 */
	public List<WrapLookup> getProducts(String productName){
		List<WrapLookup> lstProducts = new ArrayList<>();
		try{
			OpenConn();
		StringBuilder query =new StringBuilder();
		query.append("select id, name from test89__product__c");
		if(productName!=null && !productName.equals("")){
			query.append(" where name='"+productName+"'");
		}else{
			query.append(" limit 10");
		}
		System.out.println("query="+query.toString());
		QueryResult sr = con.query(query.toString());
		SObject[] records = sr.getRecords();
		if (records != null && records.length > 0) {
			for (SObject brd : records) {
				Test89__Product__c tProduct = (Test89__Product__c)brd;
				WrapLookup lookup = new WrapLookup();
				lookup.setId(tProduct.getId());
				lookup.setName(tProduct.getName());
				lstProducts.add(lookup);
			}	
		}
		} catch (ConnectionException ce) {
			ce.printStackTrace();
			System.out.print("exception");
		}
		return lstProducts;
	}
	
	/**
	 * Primary Channel Lookup
	 * @param primaryChannel
	 * @return
	 */
	public List<WrapLookup> getPrimaryChannels(String primaryChannel){
		List<WrapLookup> lsChannels = new ArrayList<>();
		try{
			OpenConn();
		StringBuilder query =new StringBuilder();
		query.append("select id, name from test89__Channel__c");
		if(primaryChannel!=null && !primaryChannel.equals("")){
			query.append(" where name='"+primaryChannel+"'");
		}else{
			query.append(" limit 10");
		}
		System.out.println("query="+query.toString());
		QueryResult sr = con.query(query.toString());
		SObject[] records = sr.getRecords();
		if (records != null && records.length > 0) {
			for (SObject brd : records) {
				Test89__Channel__c tchannel = (Test89__Channel__c)brd;
				WrapLookup lookup = new WrapLookup();
				lookup.setId(tchannel.getId());
				lookup.setName(tchannel.getName());
				lsChannels.add(lookup);
			}	
		}
		} catch (ConnectionException ce) {
			ce.printStackTrace();
			System.out.print("exception");
		}
		return lsChannels;
	}
	public List<WrapLookup> getAccounts(String accountName){
		List<WrapLookup> lsAccounts = new ArrayList<>();
		try{
			OpenConn();
		StringBuilder query =new StringBuilder();
		query.append("select id, name from account");
		if(accountName!=null && !accountName.equals("")){
			query.append(" where name='"+accountName+"'");
		}else{
			query.append(" limit 10");
		}
		QueryResult sr = con.query(query.toString());
		SObject[] records = sr.getRecords();
		if (records != null && records.length > 0) {
			for (SObject brd : records) {
				Account tacc = (Account)brd;
				WrapLookup lookup = new WrapLookup();
				lookup.setId(tacc.getId());
				lookup.setName(tacc.getName());
				lsAccounts.add(lookup);
			}	
		}
		} catch (ConnectionException ce) {
			ce.printStackTrace();
			System.out.print("exception");
		}
		return lsAccounts;
	}
	
	public void createGoal(List<WrapAccount> lstReviewAccounts,StrategicGoal in_goal){
		List<Test89__Strategic_Goal__c> sgGoalList = new ArrayList<Test89__Strategic_Goal__c>();
		// Insert Mass_Batch
		try {
			Test89__Mass_Assign_Batch__c massBatch= new Test89__Mass_Assign_Batch__c();
			System.out.println("massBatch=="+massBatch);
			Test89__Mass_Assign_Batch__c[] arrMassBatch={massBatch};
			System.out.println("arrMassBatch=="+arrMassBatch);
			OpenConn();
			SaveResult[] results=con.create(arrMassBatch);
			if(results!=null && results.length>0 && results[0].isSuccess()){
				System.out.println("results[0].getId()=="+results[0].getId());
				System.out.println("lstReviewAccounts=="+lstReviewAccounts);
				for(WrapAccount account:lstReviewAccounts){
					Test89__Strategic_Goal__c goal = new Test89__Strategic_Goal__c();
					goal.setTest89__Completed_Dt__c(in_goal.getCompletedDate());
					goal.setTest89__Goal_Note__c(in_goal.getGoalNote());
					goal.setTest89__Goal_Status__c(in_goal.getGoalStatus());
					goal.setTest89__Goal_Type__c(in_goal.getGoalType());
					goal.setName(in_goal.getName());
					goal.setTest89__Start_Dt__c(in_goal.getStartDate());
					goal.setTest89__Acct__c(account.getId());
					goal.setRecordTypeId(in_goal.getRecordTypeId());
					goal.setTest89__Program__c(in_goal.getProgramc());
					goal.setTest89__Mass_Assign_Batch__c(results[0].getId());
					sgGoalList.add(goal);
				}
			}
			OpenConn();
			System.out.println("sgGoalList=="+sgGoalList);
			System.out.println("sgGoalList=="+sgGoalList.size());
			SaveResult[] fresults=con.create(sgGoalList.toArray(new Test89__Strategic_Goal__c[sgGoalList.size()]));
			for(SaveResult rs:fresults){
				if(rs!=null && rs.isSuccess()){
					System.out.println("id=="+rs.getId());
				}else{
					System.out.println("error=="+Arrays.toString(rs.getErrors()));
				}
			}
		} catch (ConnectionException ce) {
			ce.printStackTrace();
			System.out.print("exception");
		}
	}
	/**
	 * Search Accounts
	 * @param inputAccount
	 * @return
	 */
	public List<WrapAccount> searchAccounts(InputAccount inputAccount) {
		List<WrapAccount> lstAccounts = new ArrayList<WrapAccount>();
		try {
			OpenConn();

			String soqlQuery = "SELECT ID, NAME,test89__Market__r.Name, Phone, Recordtype.Name FROM ACCOUNT";
			StringBuilder whQuery = new StringBuilder();
			boolean columnSet=false;
			//Id
			boolean isId = false;
			if(inputAccount.getId()!=null && !inputAccount.getId().equals("")){
				if(whQuery.length()==0){
					whQuery.append(" WHERE ID IN ("+inputAccount.getId()+")");	
				}else{
					whQuery.append(" AND ID IN ("+inputAccount.getId()+")");
				}
				whQuery.append(" ");
				isId=true;
			}
			//Account Name
			if(inputAccount.getAccountName()!=null && !inputAccount.getAccountName().equals("")){
				if(whQuery.length()==0){
					whQuery.append(" WHERE NAME like '%"+inputAccount.getAccountName()+"%'");	
				}else{
					whQuery.append(" AND NAME like '%"+inputAccount.getAccountName()+"%'");
				}
				whQuery.append(" ");
			}
			// Market
			if(inputAccount.getMarket()!=null && !inputAccount.getMarket().equals("")){
				if(whQuery.length()==0){
					whQuery.append(" WHERE test89__Market__r.Name like '%"+inputAccount.getMarket()+"%'");	
				}else{
					whQuery.append(" AND test89__Market__r.Name like '%"+inputAccount.getMarket()+"%'");					
				}
			}
			// Parent Account
			if(inputAccount.getParentAccountId()!=null && !inputAccount.getParentAccountId().equals("")){
				if(whQuery.length()==0){
					whQuery.append(" WHERE parentid='"+inputAccount.getParentAccountId()+"'");	
				}else{
					whQuery.append(" AND parentid='"+inputAccount.getParentAccountId()+"'");					
				}
			}
			// Brand
			if(inputAccount.getBrandId()!=null && !inputAccount.getBrandId().equals("")){
				if(whQuery.length()==0){
					whQuery.append(" WHERE ID IN (SELECT test89__Acct__c FROM test89__JCI_Brand__c WHERE test89__Brand__c ='"+inputAccount.getBrandId()+"')");	
				}else{
					whQuery.append(" AND ID IN (SELECT test89__Acct__c FROM test89__JCI_Brand__c WHERE test89__Brand__c ='"+inputAccount.getBrandId()+"')");						
				}
			}
			// Product Name
			if(inputAccount.getProductId()!=null && !inputAccount.getProductId().equals("")){
				if(whQuery.length()==0){
					whQuery.append(" WHERE ID IN (SELECT test89__Acct__c FROM test89__Product_Line__c WHERE test89__Product__c  ='"+inputAccount.getProductId()+"')");	
				}else{
					whQuery.append(" AND ID IN (SELECT test89__Acct__c FROM test89__Product_Line__c WHERE test89__Product__c  ='"+inputAccount.getProductId()+"')");					
				}
			}
			// Type
			if(inputAccount.getType()!=null && !inputAccount.getType().equals("")){
				if(whQuery.length()==0){
					whQuery.append(" WHERE Type='"+inputAccount.getType()+"'");	
				}else{
					whQuery.append(" AND Type='"+inputAccount.getType()+"'");
				}
			}
			//Primary Channel
			if(inputAccount.getPrimaryChannelId()!=null && !inputAccount.getPrimaryChannelId().equals("")){
				if(whQuery.length()==0){
					whQuery.append(" WHERE test89__Primary_Channel__c='"+inputAccount.getPrimaryChannelId()+"'");	
				}else{
					whQuery.append(" AND test89__Primary_Channel__c='"+inputAccount.getPrimaryChannelId()+"'");
				}
			}
			// Account Status
			if(inputAccount.getAccountStatus()!=null && !inputAccount.getAccountStatus().equals("")){
				if(whQuery.length()==0){
					whQuery.append(" WHERE Test89__Is_Active__c ="+inputAccount.getAccountStatus()+"");	
				}else{
					whQuery.append(" AND Test89__Is_Active__c ="+inputAccount.getAccountStatus()+"");					
				}
			}
			
			// Brand Status
			if(inputAccount.getBrandStatus()!=null && !inputAccount.getBrandStatus().equals("")){
				if(whQuery.length()==0){
					whQuery.append(" WHERE Test89__Brand_Status__c='"+inputAccount.getBrandStatus()+"'");	
				}else{
					whQuery.append(" AND Test89__Brand_Status__c='"+inputAccount.getBrandStatus()+"'");
				}
			}
			if(whQuery.length()>0){
				soqlQuery+=" "+whQuery.toString();
			}
			if(!isId){
				if(inputAccount.getPageNo()!=0 && inputAccount.getNoOfRecords()!=0){
					int skipRecords=(inputAccount.getNoOfRecords()*(inputAccount.getPageNo()-1));
					soqlQuery+=" LIMIT "+inputAccount.getNoOfRecords();
					if(skipRecords!=0){
						soqlQuery+=" OFFSET  "+skipRecords;
					}
				}
			}
			System.out.print(soqlQuery);
			QueryResult sr = con.query(soqlQuery);
			SObject[] records = sr.getRecords();
			if (records != null && records.length > 0) {
				for (SObject acc : records) {
					Account account = (Account) acc;
					WrapAccount objAccount =new WrapAccount();
					objAccount.setId(account.getId());
					objAccount.setName(account.getName());
					objAccount.setPhone(account.getPhone());
					if(account.getTest89__Market__r()!=null){
						objAccount.setMarket(account.getTest89__Market__r().getName());
					}
					if(account.getRecordType()!=null){
						objAccount.setRecordtype(account.getRecordType().getName());
					}
					lstAccounts.add(objAccount);
//					System.out.print("Account: " + account.getRecordType());
				}
			} else {
				System.out.println("No records found for the search.");
			}
		} catch (ConnectionException ce) {
			ce.printStackTrace();
			System.out.print("exception");
		}
		return lstAccounts;
	}
}

