package id.co.bjj.library.general.persistence.models;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import id.co.bjj.library.general.persistence.enums.StatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class GeneralModel implements Serializable {
	private static final long serialVersionUID = 8722777305502975963L;

	@Column(name = "created_date", nullable = true, insertable = true, updatable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdDate;

	@Column(name = "created_by", nullable = true, insertable = true, updatable = false, length = 100, precision = 0)
	@CreatedBy
	private String createdBy;

	@Column(name = "updated_date", nullable = true, insertable = true, updatable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@LastModifiedDate
	private Date updatedDate;

	@Column(name = "updated_by", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
	@LastModifiedBy
	private String updatedBy;

	@Column(name = "approved_date", nullable = true, insertable = true, updatable = true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date approvedDate;

	@Column(name = "approved_by", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
	private String approvedBy;

	@Column(name = "status", nullable = true, insertable = true, updatable = true, length = 10, precision = 0)
	@Enumerated(EnumType.STRING)
	private StatusEnum status;

	public GeneralModel() {
	}

	@PrePersist
	protected void onCreate() {
		createdDate = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedDate = new Date();
	}

	public Date getCreatedDate() {
		return createdDate == null ? null : new Date(createdDate.getTime());
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate == null ? null : new Date(createdDate.getTime());
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedDate() {
		return updatedDate == null ? null : new Date(updatedDate.getTime());
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate == null ? null : new Date(updatedDate.getTime());
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getApprovedDate() {
		return approvedDate == null ? null : new Date(approvedDate.getTime());
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate == null ? null : new Date(approvedDate.getTime());
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}
}
