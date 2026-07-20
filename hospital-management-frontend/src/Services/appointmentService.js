import api from "./api";

// Get all appointments
export const getAppointments = () =>
    api.get("/appointments");

// Get appointment by ID
export const getAppointmentById = (id) =>
    api.get(`/appointments/${id}`);

// Create appointment
export const createAppointment = (appointment) =>
    api.post("/appointments", appointment);

// Update appointment
export const updateAppointment = (id, appointment) =>
    api.put(`/appointments/${id}`, appointment);

// Delete appointment
export const deleteAppointment = (id) =>
    api.delete(`/appointments/${id}`);

// Get appointments by doctor
export const getAppointmentsByDoctor = (doctorId) =>
    api.get(`/appointments/doctor/${doctorId}`);

// Get appointments by patient
export const getAppointmentsByPatient = (patientId) =>
    api.get(`/appointments/patient/${patientId}`);