import { useEffect, useState } from "react";
import {
  FaPlus,
  FaSearch,
  FaEdit,
  FaTrash,
  FaFileInvoiceDollar,
} from "react-icons/fa";

import API from "../api/axiosConfig";

function Billings() {
  const [billings, setBillings] = useState([]);
  const [appointments, setAppointments] = useState([]);
  const [search, setSearch] = useState("");
  const [showModal, setShowModal] = useState(false);
  const [isEdit, setIsEdit] = useState(false);

  const [formData, setFormData] = useState({
    amount: "",
    appointmentId: "",
  });

  const [editId, setEditId] = useState(null);

  useEffect(() => {
    fetchBillings();
    fetchAppointments();
  }, []);

  // Fetch all billings
  const fetchBillings = async () => {
    try {
      const res = await API.get("/billings");
      setBillings(res.data);
    } catch (error) {
      console.error(error);
    }
  };

  // Fetch appointments for dropdown
  const fetchAppointments = async () => {
    try {
      const res = await API.get("/appointments");
      setAppointments(res.data);
    } catch (error) {
      console.error(error);
    }
  };

  // Handle input change
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  // Add Billing
  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      await API.post("/billings", {
        amount: Number(formData.amount),
        appointmentId: Number(formData.appointmentId),
      });

      fetchBillings();
      closeModal();
    } catch (error) {
      console.error(error);
      alert("Failed to add billing");
    }
  };

  // Edit Billing
  const handleEdit = (billing) => {
    setIsEdit(true);
    setEditId(billing.id);

    setFormData({
      amount: billing.amount,
      appointmentId: billing.appointmentId,
    });

    setShowModal(true);
  };

  // Update Billing
  const handleUpdate = async (e) => {
    e.preventDefault();

    try {
      const payload = {
        amount: Number(formData.amount),
        appointmentId: Number(formData.appointmentId),
      };

      console.log("Updating:", payload);

      await API.put(`/billings/${editId}`, payload);

      // refresh latest data
      await fetchBillings();

      closeModal();

    } catch (error) {
      console.error("Update Error:", error.response?.data || error);
      alert("Failed to update billing");
    }
  };

  // Delete Billing
  const handleDelete = async (id) => {
    if (!window.confirm("Delete this billing?")) return;

    try {
      await API.delete(`/billings/${id}`);
      fetchBillings();
    } catch (error) {
      console.error(error);
    }
  };

  // Close modal
  const closeModal = () => {
    setShowModal(false);
    setIsEdit(false);
    setEditId(null);

    setFormData({
      amount: "",
      appointmentId: "",
    });
  };

  // Search filter
  const filteredBillings = billings.filter((b) =>
    b.appointmentId?.toString().includes(search)
  );

  return (
    <div className="p-6">
      {/* Header */}
      <div className="flex justify-between items-center mb-6">
        <div className="flex items-center gap-3">
          <FaFileInvoiceDollar className="text-3xl text-green-600" />
          <h1 className="text-2xl font-bold text-gray-800">Billings</h1>
        </div>

        <button
          onClick={() => setShowModal(true)}
          className="bg-green-600 hover:bg-green-700 text-white px-4 py-2 rounded-lg flex items-center gap-2"
        >
          <FaPlus /> Add Billing
        </button>
      </div>

      {/* Search */}
      <div className="relative mb-4 max-w-sm">
        <FaSearch className="absolute left-3 top-3 text-gray-400" />
        <input
          type="text"
          placeholder="Search by Appointment ID"
          value={search}
          onChange={(e) => setSearch(e.target.value)}
          className="w-full pl-10 pr-4 py-2 border rounded-lg focus:ring-2 focus:ring-green-500 outline-none"
        />
      </div>

      {/* Table */}
      <div className="bg-white rounded-xl shadow overflow-hidden">
        <table className="w-full">
          <thead className="bg-gray-100">
            <tr>
              <th className="text-center px-6 py-3">ID</th>
              <th className="text-center px-6 py-3">Amount</th>
              <th className="text-center px-6 py-3">Appointment ID</th>
              <th className="text-center px-6 py-3">Actions</th>
            </tr>
          </thead>

          <tbody>
            {filteredBillings.length > 0 ? (
              filteredBillings.map((billing) => (
                <tr key={billing.id} className="border-t hover:bg-gray-50">

                  {/* ID */}
                  <td className="text-center px-6 py-4 font-medium">
                    {billing.id}
                  </td>

                  {/* Amount */}
                  <td className="text-center px-6 py-4 font-semibold text-green-600">
                    ₹{billing.amount}
                  </td>

                  {/* Appointment ID */}
                  <td className="text-center px-6 py-4 font-medium">
                    {billing.appointmentId}
                  </td>

                  {/* Actions */}
                  <td className="text-center px-6 py-4">
                    <div className="flex justify-center gap-3">
                      <button
                        onClick={() => handleEdit(billing)}
                        className="text-blue-600 hover:text-blue-800 transition"
                      >
                        <FaEdit />
                      </button>

                      <button
                        onClick={() => handleDelete(billing.id)}
                        className="text-red-600 hover:text-red-800 transition"
                      >
                        <FaTrash />
                      </button>
                    </div>
                  </td>

                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="4" className="text-center py-8 text-gray-500">
                  No billings found
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>

      {/* Modal */}
      {showModal && (
        <div className="fixed inset-0 bg-black bg-opacity-40 flex items-center justify-center z-50">
          <div className="bg-white rounded-2xl p-6 w-full max-w-md">
            <h2 className="text-xl font-bold mb-4">
              {isEdit ? "Update Billing" : "Add Billing"}
            </h2>

            <form
              onSubmit={isEdit ? handleUpdate : handleSubmit}
              className="space-y-4"
            >
              <div>
                <label className="block text-sm font-medium mb-1">
                  Amount
                </label>
                <input
                  type="number"
                  step="0.01"
                  name="amount"
                  value={formData.amount}
                  onChange={handleChange}
                  required
                  className="w-full border rounded-lg px-3 py-2 focus:ring-2 focus:ring-green-500 outline-none"
                />
              </div>

              <div>
                <label className="block text-sm font-medium mb-1">
                  Appointment
                </label>

                <select
                  name="appointmentId"
                  value={formData.appointmentId}
                  onChange={handleChange}
                  required
                  className="w-full border rounded-lg px-3 py-2 focus:ring-2 focus:ring-green-500 outline-none"
                >
                  <option value="">Select Appointment</option>

                  {appointments.map((a) => (
                    <option key={a.id} value={a.id}>
                      Appointment #{a.id}
                    </option>
                  ))}
                </select>
              </div>

              <div className="flex justify-end gap-3 pt-2">
                <button
                  type="button"
                  onClick={closeModal}
                  className="px-4 py-2 border rounded-lg hover:bg-gray-100"
                >
                  Cancel
                </button>

                <button
                  type="submit"
                  className="px-4 py-2 bg-green-600 text-white rounded-lg hover:bg-green-700"
                >
                  {isEdit ? "Update" : "Save"}
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
}

export default Billings;