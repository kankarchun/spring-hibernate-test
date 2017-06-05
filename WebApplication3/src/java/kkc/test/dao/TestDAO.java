/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkc.test.dao;

import java.util.List;
import kkc.test.model.TestDB;
/**
 *
 * @author 1
 */
public interface TestDAO {
    
    public void save(TestDB t);
    
    public List<TestDB> list();
    
    public List<TestDB> select(int id);
    
    public int insert(String name,int id);
    
    public void update(String name,int id);
    
    public void delete(int id);
    
    public List<TestDB> criteria();
}
