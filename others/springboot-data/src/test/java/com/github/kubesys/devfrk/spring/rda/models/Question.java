package com.github.kubesys.devfrk.spring.rda.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "question")
public class Question {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @Column(name = "subject", length = 50)
    private String subject;

    @Size(max = 255)
    @Column(name = "label")
    private String label;

    @Column(name = "parent_id")
    private Long parentId;

    @Size(max = 255)
    @Column(name = "parent_content")
    private String parentContent;

    @NotNull
    @Column(name = "content", nullable = false, length = Integer.MAX_VALUE)
    private String content;

    @Column(name = "options", length = Integer.MAX_VALUE)
    private String options;

    @Column(name = "analysis", length = Integer.MAX_VALUE)
    private String analysis;

    @Column(name = "method", length = Integer.MAX_VALUE)
    private String method;

    @Column(name = "discuss", length = Integer.MAX_VALUE)
    private String discuss;

    @Column(name = "display_answer", length = Integer.MAX_VALUE)
    private String displayAnswer;

    @Column(name = "answers", length = Integer.MAX_VALUE)
    private String answers;

    @Column(name = "source", length = Integer.MAX_VALUE)
    private String source;

    @Size(max = 50)
    @NotNull
    @Column(name = "create_name", nullable = false, length = 50)
    private String createName;

    @Size(max = 50)
    @NotNull
    @Column(name = "update_name", nullable = false, length = 50)
    private String updateName;

    @NotNull
    @Column(name = "create_time", nullable = false)
    private OffsetDateTime createTime;

    @NotNull
    @Column(name = "update_time", nullable = false)
    private OffsetDateTime updateTime;

    @NotNull
    @Column(name = "is_valid", nullable = false)
    private Boolean isValid = false;

    @Size(max = 50)
    @Column(name = "book_name", length = 50)
    private String bookName;

    @Column(name = "grade")
    private Integer grade;

    @Size(max = 50)
    @Column(name = "number", length = 50)
    private String number;

    @Column(name = "page")
    private Integer page;

    @Size(max = 50)
    @Column(name = "type", length = 50)
    private String type;

    @Column(name = "degree", precision = 3, scale = 2)
    private BigDecimal degree;

    @NotNull
    @Column(name = "picked_count", nullable = false)
    private Integer pickedCount;

    @Size(max = 50)
    @Column(name = "audit_name", length = 50)
    private String auditName;

    @NotNull
    @Column(name = "is_audited", nullable = false)
    private Boolean isAudited = false;

    @Size(max = 1023)
    @Column(name = "option_pictures", length = 1023)
    private String optionPictures;

    @Size(max = 7)
    @Column(name = "version", length = 7)
    private String version;

    @Column(name = "school_org_id")
    private Long schoolOrgId;

    @Size(max = 64)
    @Column(name = "school_org_name", length = 64)
    private String schoolOrgName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getParentContent() {
		return parentContent;
	}

	public void setParentContent(String parentContent) {
		this.parentContent = parentContent;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getDiscuss() {
		return discuss;
	}

	public void setDiscuss(String discuss) {
		this.discuss = discuss;
	}

	public String getDisplayAnswer() {
		return displayAnswer;
	}

	public void setDisplayAnswer(String displayAnswer) {
		this.displayAnswer = displayAnswer;
	}

	public String getAnswers() {
		return answers;
	}

	public void setAnswers(String answers) {
		this.answers = answers;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public OffsetDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(OffsetDateTime createTime) {
		this.createTime = createTime;
	}

	public OffsetDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(OffsetDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getDegree() {
		return degree;
	}

	public void setDegree(BigDecimal degree) {
		this.degree = degree;
	}

	public Integer getPickedCount() {
		return pickedCount;
	}

	public void setPickedCount(Integer pickedCount) {
		this.pickedCount = pickedCount;
	}

	public String getAuditName() {
		return auditName;
	}

	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}

	public Boolean getIsAudited() {
		return isAudited;
	}

	public void setIsAudited(Boolean isAudited) {
		this.isAudited = isAudited;
	}

	public String getOptionPictures() {
		return optionPictures;
	}

	public void setOptionPictures(String optionPictures) {
		this.optionPictures = optionPictures;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Long getSchoolOrgId() {
		return schoolOrgId;
	}

	public void setSchoolOrgId(Long schoolOrgId) {
		this.schoolOrgId = schoolOrgId;
	}

	public String getSchoolOrgName() {
		return schoolOrgName;
	}

	public void setSchoolOrgName(String schoolOrgName) {
		this.schoolOrgName = schoolOrgName;
	}
    
    

}