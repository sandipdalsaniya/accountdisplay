package service;

import java.util.List;

import pojo.InputAccount;
import pojo.StrategicGoal;
import pojo.WrapAccount;
import pojo.WrapLookup;
import utils.SalesforceUtils;

public class AccountServices {
	public List<WrapAccount> getWrapAccounts(InputAccount inputAccount) {
		SalesforceUtils objSFDCConnect = new SalesforceUtils();
		return objSFDCConnect.searchAccounts(inputAccount);
    }
	public List<WrapLookup> getWrapLookup(String type,String lookupName) {
		SalesforceUtils objSFDCConnect = new SalesforceUtils();
		if(type!=null && type.equals("Brand")){
			return objSFDCConnect.getBrands(lookupName);
		}else if(type!=null && type.equals("Product")){
			return objSFDCConnect.getProducts(lookupName);
		}else if(type!=null && type.equals("PrimaryChannel")){
			return objSFDCConnect.getPrimaryChannels(lookupName);
		}else{
			return objSFDCConnect.getAccounts(lookupName);
		}
    }
	public void createGoal(List<WrapAccount> lstReviewAccounts,StrategicGoal in_goal){
		SalesforceUtils objSFDCConnect = new SalesforceUtils();
		objSFDCConnect.createGoal(lstReviewAccounts, in_goal);
	}
}
