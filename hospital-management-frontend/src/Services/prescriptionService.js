import api from "./api";

// Get all prescriptions
export const getPrescriptions = () =>
    api.get("/prescriptions");

// Get prescription by ID
export const getPrescriptionById = (id) =>
    api.get(`/prescriptions/${id}`);

// Create prescription
export const createPrescription = (prescription) =>
    api.post("/prescriptions", prescription);

// Update prescription
export const updatePrescription = (id, prescription) =>
    api.put(`/prescriptions/${id}`, prescription);

// Delete prescription
export const deletePrescription = (id) =>
    api.delete(`/prescriptions/${id}`);

// Get prescriptions by patient
export const getPrescriptionsByPatient = (patientId) =>
    api.get(`/prescriptions/patient/${patientId}`);

// Get prescriptions by doctor
export const getPrescriptionsByDoctor = (doctorId) =>
    api.get(`/prescriptions/doctor/${doctorId}`);