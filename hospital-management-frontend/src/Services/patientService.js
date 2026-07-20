import api from "./api";

// Get all patients
export const getPatients = () =>
    api.get("/patients");

// Get patient by ID
export const getPatientById = (id) =>
    api.get(`/patients/${id}`);

// Create new patient
export const createPatient = (patient) =>
    api.post("/patients", patient);

// Update patient
export const updatePatient = (id, patient) =>
    api.put(`/patients/${id}`, patient);

// Delete patient
export const deletePatient = (id) =>
    api.delete(`/patients/${id}`);