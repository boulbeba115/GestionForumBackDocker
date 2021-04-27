package tn.Forum.Main.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.Forum.Main.Models.ReportPost;
import tn.Forum.Main.queryManaging.Reports.SimplifyPostReport;

public interface ReportRepo extends JpaRepository<ReportPost, Long>{

	ReportPost findByUserReportUserId(String userId);

	ReportPost findByUserReportUserIdAndReportedPostPostId(String userId, String postId);

	List<SimplifyPostReport> findAllProjectedBy();
	
	

}
