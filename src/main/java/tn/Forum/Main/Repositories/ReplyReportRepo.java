package tn.Forum.Main.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.Forum.Main.Models.ReportReply;
import tn.Forum.Main.queryManaging.Reports.SimplifiedReplyRep;

public interface ReplyReportRepo extends JpaRepository<ReportReply, Long>{


	ReportReply findByUserRepReportUserIdAndReportedReplyReplyId(String userId, String replyId);

	List<SimplifiedReplyRep> findAllProjectedBy();

}
