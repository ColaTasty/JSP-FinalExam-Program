package table;

import abstracts.TableItem;
import global.config.DBConnecter;

import java.sql.SQLException;

/**
 * @author 杨子豪，温剑
 */
public class Post_ResponseTableItem  extends TableItem {

    public Post_ResponseTableItem (DBConnecter dbConnecter) {
        super("post_responses");
        this.dbConnecter = dbConnecter;
    }

    /**
     * @return boolean|null
     */
    public String [][] isQuery() {
        try {
            this.sql = "SELECT * FROM " + this.getTableName();
            this.preparedStatement = this.getDbConnecter().getPreparedStatement(this.sql);
            this.resultSet = this.preparedStatement.executeQuery();
            
            ResultSetMetaData metaData=rs.getMetaData();
			int columnCount=metaData.getColumnCount();
			int rowNumber=this.resultSet.getRow();
			String [][]tableResult=new String[rowNumber][columnCount];
			this.resultSet.beforeFirst();
			int i=0;
			while(this.resultSet.next()){
				for(int k=0;k<columnCount;k++)
					tableResult[i][k]=this.resultSet.getString(k+1);
			i++;
			}
			return tableResult;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @return boolean
     */
    public boolean isRelease(int pid,int uid,String content,long time) {
        try {
            this.sql = "INSERT INTO " + this.getTableName() + "(post_id,user_id,content,time) VALUES(?,?,?,?)";
            this.preparedStatement = this.getDbConnecter().getPreparedStatement(this.sql);
            this.preparedStatement.setInt(1, pid);
            this.preparedStatement.setInt(2, uid);
            this.preparedStatement.setString(3, content);
            this.preparedStatement.setLong(4, time);
            return this.preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
