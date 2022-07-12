
package com.project.testcases.db;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.DeleteOneModel;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.ReplaceOneModel;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.UpdateOneModel;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.model.WriteModel;
import com.project.utilities.MongoConnection;
import com.project.utilities.MongoDBGenericUtils;
import com.project.testbase.TestBase;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lt;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
public class MongoDBTestsCRUD extends TestBase {
	
 	MongoConnection mconn=null;
	MongoDBGenericUtils md=null;
	@BeforeMethod
	public void getConnection() throws InterruptedException {
		String db=prop.getProperty("mongodatabase");
		String coll=prop.getProperty("mongocollection");

        md=new MongoDBGenericUtils(db,coll);
		mconn=md.getMongoDbConnection();
	}
	
	
@Test
	public void FindMultiRecords()
	{
	
    	  Bson projectionFields = Projections.fields(
                  Projections.include("title", "imdb"),
                  Projections.excludeId());
    	  
    	    MongoCollection<Document> collection = mconn.getCollection();
    	    
    	    FindIterable<Document>	   coll=collection.find(lt("runtime", 15))
                     .projection(projectionFields)
                     .sort(Sorts.descending("title"));
    	    
    	    MongoCursor<Document> cursor=	md.FindRecord(mconn,coll);
    	try
    	{
        while(cursor.hasNext()) {
            log4j("From test:"+cursor.next().toJson());
        }
    	}
    	finally
    	{
    		cursor.close();
    	}
	}
@Test
	public void FindSingleRecord()
	{

		
        Bson projectionFields = Projections.fields(
                Projections.include("title", "imdb"),
                Projections.excludeId());
        MongoCollection<Document> collection = mconn.getCollection();
        FindIterable<Document> doc= collection.find(eq("title", "The Room"))
        .projection(projectionFields)
        .sort(Sorts.descending("imdb.rating"));
        Document  cursor=null;
        try
        {
        cursor=	md.FindSingleRecord(mconn,doc);
        if (cursor == null) {
            log4j("No results found.");
        } else {
            log4j(cursor.toJson());
        }
        }
    	catch(Exception e)
    	{
    	logError(e.getMessage());
    	}

	}
	
	//@Test
	public void InsertSingleRecord()
	{
        MongoCollection<Document> collection = mconn.getCollection();
        Document doc= new Document()
                .append("_id", new ObjectId())
                .append("title", "Ski Bloopers")
                .append("genres", Arrays.asList("Documentary", "Comedy"));
      
        try
        {
        		if(md.InsertOneRecord(collection,doc))
        		{
        		    log4j("Inserted record successfully");
        		}
        		else
        		{
        		    log4j("Insert operation failed");
        		}

        }
    	catch(Exception e)
    	{
    	logError(e.getMessage());
    	}

	}
	
	//@Test
	public void InsertMultiRecord()
	{

        MongoCollection<Document> collection = mconn.getCollection();
        List<Document> movieList = Arrays.asList(
                new Document().append("_id", new ObjectId()).append("title", "Short Circuit 4").append("genres", Arrays.asList("Action", "Comedy")),
                new Document().append("_id", new ObjectId()).append("title", "The Lego Frozen Movie 4").append("genres", Arrays.asList("Science Fiction", "Comedy")));
 
      
        try
        {
        		if(md.InsertMultiRecords(collection,movieList))
        		{
        		    log4j("Inserted record successfully");
        		}
        		else
        		{
        		    log4j("Insert operation failed");
        		}

        }
    	catch(Exception e)
    	{
    	logError(e.getMessage());
    	}

	}
	
	//@Test
	public void UpdateSingleRecord()
	{
        MongoCollection<Document> collection = mconn.getCollection();
        Document query = new Document().append("title",  "Cool Runnings");
        Bson updates = Updates.combine(
                Updates.set("runtime", 99),
                Updates.addToSet("genres", "Sports"),
                Updates.currentTimestamp("lastUpdated"));
                
        try
        {
        		if(md.UpdateOneRecord(collection,query,updates))
        		{
        		    log4j("Updated recordsuccessfully");
        		}
        		else
        		{
        		    log4j("Update operation failed");
        		}

        }
    	catch(Exception e)
    	{
    	logError(e.getMessage());
    	}

	}
	
	//@Test
	public void UpdateMultiRecords()
	{
        MongoCollection<Document> collection = mconn.getCollection();
        Bson query = gt("num_mflix_comments", 50);
        Bson updates = Updates.combine(
                Updates.addToSet("genres", "Frequently Discussed"),
                Updates.currentTimestamp("lastUpdated"));
      
        try
        {
        		if(md. UpdateMultiRecords(collection,query,updates))
        		{
        		    log4j("Updated record successfully");
        		}
        		else
        		{
        		    log4j("Update operation failed");
        		}

        }
    	catch(Exception e)
    	{
    	logError(e.getMessage());
    	}

	}
	
//	@Test
	public void DeleteSingleRecord()
	{
        MongoCollection<Document> collection = mconn.getCollection();
        Bson query = eq("title", "The Garbage Pail Kids Movie");
      
        try
        {
        		if(md.DeleteOneRecord(collection,query))
        		{
        		    log4j("Record Deeleted Successfully");
        		}
        		else
        		{
        		    log4j("Delete operation failed");
        		}

        }
    	catch(Exception e)
    	{
    	logError(e.getMessage());
    	}

	}
//	@Test
	public void DeleteMultiRecords()
	{
        MongoCollection<Document> collection = mconn.getCollection();
        Bson query = lt("imdb.rating", 1.9);
      
        try
        {
        		if(md.DeleteOneRecord(collection,query))
        		{
        		    log4j("Records Deleted Successfully");
        		}
        		else
        		{
        		    log4j("Delete operation failed");
        		}

        }
    	catch(Exception e)
    	{
    	logError(e.getMessage());
    	}

	}
	
	//@Test
	public void replaceRecord()
	{
        MongoCollection<Document> collection = mconn.getCollection();
        Bson query = eq("title", "Music of the Heart");
        Document replaceDocument = new Document().
                append("title", "50 Violins").
                append("fullplot", " A dramatization of the true story of Roberta Guaspari who co-founded the Opus 118 Harlem School of Music");
        
      
        try
        {
        		if(md.replaceRecord(collection, replaceDocument, query))
        		{
        		    log4j("Replaced record Successfully");
        		}
        		else
        		{
        		    log4j("Replace operation failed");
        		}

        }
    	catch(Exception e)
    	{
    	logError(e.getMessage());
    	}

	}
	//@Test
		public void bulkWriteRecords()
		{
	        MongoCollection<Document> collection = mconn.getCollection();
	         List<WriteModel<? extends Object>> list=Arrays.asList(
                     new InsertOneModel<>(new Document("name", "A Sample Movie")),
                     new InsertOneModel<>(new Document("name", "Another Sample Movie")),
                     new InsertOneModel<>(new Document("name", "Yet Another Sample Movie")),
                     new UpdateOneModel<>(new Document("name", "A Sample Movie"),
                             new Document("$set", new Document("name", "An Old Sample Movie")),
                             new UpdateOptions().upsert(true)),
                     new DeleteOneModel<>(new Document("name", "Yet Another Sample Movie")),
                     new ReplaceOneModel<>(new Document("name", "Yet Another Sample Movie"),
                             new Document("name", "The Other Sample Movie").append("runtime",  "42"))
                     );
	        try
	        {
	        		if(md.bulkWrite(collection, list))
	        		{
	        		    log4j("Bulk Write Success");
	        		}
	        		else
	        		{
	        		    log4j("Bulk operation failed");
	        		}

	        }
	    	catch(Exception e)
	    	{
	    	logError(e.getMessage());
	    	}

		}
	@AfterMethod
	public void close()
	{
		 mconn.getMongoClient().close();
	}
}
