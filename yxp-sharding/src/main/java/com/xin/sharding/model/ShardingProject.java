package com.xin.sharding.model;

public class ShardingProject {

    private Integer projectId;
    private String projectName;


    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sharding_project.project_id
     *
     * @return the value of sharding_project.project_id
     *
     * @mbg.generated Tue Oct 09 14:13:02 CST 2018
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sharding_project.project_id
     *
     * @param projectId the value for sharding_project.project_id
     *
     * @mbg.generated Tue Oct 09 14:13:02 CST 2018
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sharding_project.project_name
     *
     * @return the value of sharding_project.project_name
     *
     * @mbg.generated Tue Oct 09 14:13:02 CST 2018
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sharding_project.project_name
     *
     * @param projectName the value for sharding_project.project_name
     *
     * @mbg.generated Tue Oct 09 14:13:02 CST 2018
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}