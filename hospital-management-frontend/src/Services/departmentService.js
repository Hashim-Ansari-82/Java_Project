import api from "./api";

export const getDepartments = () =>
    api.get("/departments");

export const createDepartment = (department) =>
    api.post("/departments", department);