//package ua.com.ex.reprository.impl.file;
//
//import org.hibernate.HibernateException;
//import org.hibernate.Transaction;
//
//public class PureSql {
//    
//   // private static SessionFactory factory; 
//    
//    public void start(){
//
//       // Session session = Hibernate.util.HibernateUtil.getSessionFactory().openSession();
//        //Session session = factory.openSession();
//        Transaction tx = null;
//        try{
//        //   tx = session.beginTransaction();
//           String sql = "CREATE TABLE `products` ("+
//                  "`id` int(11) unsigned NOT NULL AUTO_INCREMENT,"+
//                   "`producer_id` int(10) unsigned NOT NULL,"+
//                   "`brand_id` int(10) unsigned NOT NULL,"+
//                   "`category_id` int(10) unsigned NOT NULL,"+
//                   "`group_type` tinyint(1) unsigned NOT NULL,"+
//                   "`group_id` int(11) NOT NULL DEFAULT '0',"+
//                   "`attrgroup_id` int(10) unsigned NOT NULL,"+
//                   "`attrgroup_extra` tinyint(1) unsigned NOT NULL DEFAULT '0',"+
//                   "`desc_id` int(11) NOT NULL,"+
//                   "`parent` tinyint(1) unsigned NOT NULL DEFAULT '0',"+
//                   "`child` tinyint(1) unsigned NOT NULL DEFAULT '0',"+
//                   "`type_id` int(10) unsigned NOT NULL DEFAULT '0',"+
//                   "`type` varchar(100) NOT NULL,"+
//                   "`name` varchar(255) NOT NULL,"+
//                   "`alias` varchar(255) DEFAULT NULL,"+
//                   "`article` varchar(100) NOT NULL,"+
//                   "`enabled` tinyint(1) NOT NULL DEFAULT '1',"+
//                   "`price` decimal(10,2) NOT NULL DEFAULT '0.00',"+
//                   "`discount` smallint(6) NOT NULL DEFAULT '0',"+
//                   "`current_price` decimal(10,2) NOT NULL DEFAULT '0.00',"+
//                   "`action` tinyint(4) NOT NULL DEFAULT '0',"+
//                   "`front` tinyint(1) unsigned NOT NULL DEFAULT '0',"+
//                   "`count` mediumint(9) NOT NULL DEFAULT '0',"+
//                   "`group_count` smallint(6) NOT NULL DEFAULT '0',"+
//                   "`size` varchar(50) DEFAULT NULL,"+
//                   "`color` varchar(50) DEFAULT NULL,"+
//                   "`weight` float NOT NULL DEFAULT '0',"+
//                   "`length` varchar(32) NOT NULL DEFAULT '',"+
//                   "`image_id` int(11) DEFAULT '0',"+
//                   "`extra_images` tinyint(1) unsigned NOT NULL DEFAULT '0',"+
//                   "`video` tinyint(1) unsigned NOT NULL DEFAULT '0',"+
//                   "`files` tinyint(1) unsigned NOT NULL DEFAULT '0',"+
//                   "`review` tinyint(1) unsigned NOT NULL DEFAULT '0',"+
//                   "`date_added` date DEFAULT NULL,"+
//                   "`date_modified` date DEFAULT NULL,"+
//                   "`editor` tinyint(3) unsigned DEFAULT NULL,"+
//                   "`order_only` tinyint(1) NOT NULL DEFAULT '0',"+
//                   "`sort_order` int(11) NOT NULL DEFAULT '0',"+
//                   "`sort_backup` int(11) NOT NULL DEFAULT '0',"+
//                   "`accompany` int(11) NOT NULL DEFAULT '0',"+
//                   "`del` tinyint(1) NOT NULL DEFAULT '0',"+
//                   "`producer_name` varchar(255) NOT NULL,"+
//                   "`base_category` int(11) NOT NULL,"+
//                   "`sync` tinyint(1) unsigned NOT NULL DEFAULT '0',"+
//                   "`upd` smallint(1) NOT NULL DEFAULT '0',"+
//                   "`brandSite` tinyint(1) NOT NULL DEFAULT '1',"+
//                   "`sync_result` tinyint(1) unsigned NOT NULL DEFAULT '0',"+
//                   "PRIMARY KEY (`id`),"+
//                   "KEY `brand_id` (`brand_id`),"+
//                   "KEY `count` (`count`),"+
//                   "KEY `alias` (`alias`),"+
//                   "KEY `child` (`child`),"+
//                   "KEY `action` (`action`),"+
//                   "KEY `category_id` (`category_id`),"+
//                   "KEY `group_id` (`group_id`),"+
//                   "KEY `group_type` (`group_type`),"+
//                   "KEY `producer_id` (`producer_id`)"+
//                 ")"; 
//           session.createSQLQuery(sql).executeUpdate();           
//           tx.commit();
//        }catch (HibernateException e) {
//           if (tx!=null) tx.rollback();
//           e.printStackTrace(); 
//        }finally {
//           session.close(); 
//        }
//        
//        
//    }
//}
