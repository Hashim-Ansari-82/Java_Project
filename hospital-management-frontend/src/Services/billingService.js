import api from "./api";

// Get all bills
export const getBillings = () =>
    api.get("/billings");

// Get bill by ID
export const getBillingById = (id) =>   
    api.get(`/billings/${id}`);

// Create bill
export const createBilling = (billing) =>
    api.post("/billings", billing);

// Update bill
export const updateBilling = (id, billing) =>
    api.put(`/billings/${id}`, billing);

// Delete bill
export const deleteBilling = (id) =>
    api.delete(`/billings/${id}`);

// Get bills by patient
export const getBillingsByPatient = (patientId) =>
    api.get(`/billings/patient/${patientId}`);

// Pay bill
export const payBilling = (id) =>
    api.put(`/billings/${id}/pay`);