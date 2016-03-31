/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pojo.WrapAccount;

/**
 *
 * @author sandipkumard
 */
public class CacheManager {

    static private CacheManager instance;
    private List<WrapAccount> accounts = new ArrayList<WrapAccount>();
    private List<WrapAccount> sessionAccounts = new ArrayList<WrapAccount>();
    private Map<String,WrapAccount> reviewAccounts = new HashMap<String,WrapAccount>();
    private CacheManager() {
    }

    public static CacheManager getInstance() {
        if (null == instance) {
            instance = new CacheManager();
        }
        return instance;
    }
    public List<WrapAccount> getAccounts(){
        return accounts;
    }
    public void setAccounts(List<WrapAccount> accounts){
        this.accounts=accounts;
    }

	public Map<String, WrapAccount> getReviewAccounts() {
		return reviewAccounts;
	}

	public void setReviewAccounts(Map<String, WrapAccount> reviewAccounts) {
		this.reviewAccounts = reviewAccounts;
	}

	public List<WrapAccount> getSessionAccounts() {
		return sessionAccounts;
	}

	public void setSessionAccounts(List<WrapAccount> sessionAccounts) {
		this.sessionAccounts = sessionAccounts;
	}

    
}
