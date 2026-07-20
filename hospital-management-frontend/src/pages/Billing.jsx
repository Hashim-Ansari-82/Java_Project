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

  // Total billings count
  const totalBillings = billings.length;

  // Total revenue amount
  const totalRevenue = billings.reduce(
    (sum, billing) => sum + Number(billing.amount || 0),
    0
  );

  const billingStats = [
    {
      title: "Total Billings",
      count: totalBillings,
      icon: <FaFileInvoiceDollar />,
      bg: "from-green-500 to-emerald-600",
    },
    {
      title: "Total Revenue",
      count: `₹${totalRevenue.toLocaleString()}`,
      icon: <FaFileInvoiceDollar />,
      bg: "from-blue-500 to-indigo-600",
    },
  ];

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

      {/* <!-- BILLING STATISTICS CARDS --> */}
      <div className="grid grid-cols-2 md:grid-cols-4 gap-3 mb-5">

        {billingStats.map((item, index) => (

          <div
            key={index}
            className={`
        bg-gradient-to-r ${item.bg}
        rounded-2xl
        shadow-md
        px-3 py-2.5
        text-white
        border border-white/10
        transition-all duration-300
        hover:-translate-y-0.5
        hover:shadow-xl
        cursor-pointer
        min-h-[78px]
      `}
          >

            <div className="flex justify-between items-center h-full">

              <div className="flex-1 min-w-0">

                <p className="text-[10px] md:text-[11px] opacity-90 font-semibold uppercase tracking-wider truncate">
                  {item.title}
                </p>

                <h1 className="text-xl md:text-2xl font-extrabold mt-1 leading-none">
                  {item.count}
                </h1>

              </div>

              <div
                className="
            w-9 h-9 md:w-10 md:h-10
            rounded-xl
            bg-white/20
            flex items-center justify-center
            text-lg md:text-xl
            backdrop-blur-sm
            flex-shrink-0
            ml-3
          "
              >
                {item.icon}
              </div>

            </div>

          </div>

        ))}

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
        <div className="fixed inset-0 bg-black/30 flex items-center justify-center z-50 p-4">

          <div className="bg-white text-slate-800 rounded-3xl p-6 w-full max-w-md shadow-2xl border border-gray-100">

            <h2 className="text-2xl font-bold mb-5 text-slate-800">
              {isEdit ? "Update Billing" : "Add Billing"}
            </h2>

            <form
              onSubmit={isEdit ? handleUpdate : handleSubmit}
              className="space-y-5"
            >

              {/* Amount */}
              <div>
                <label className="block text-sm font-semibold text-slate-700 mb-2">
                  Amount
                </label>

                <input
                  type="number"
                  step="0.01"
                  name="amount"
                  value={formData.amount}
                  onChange={handleChange}
                  required
                  className="
              w-full
              border border-gray-300
              rounded-xl
              px-4 py-3
              bg-white text-slate-800
              outline-none
              focus:ring-2 focus:ring-green-500
              focus:border-green-500
              transition
            "
                  placeholder="Enter amount"
                />
              </div>

              {/* Appointment */}
              <div>
                <label className="block text-sm font-semibold text-slate-700 mb-2">
                  Appointment
                </label>

                <select
                  name="appointmentId"
                  value={formData.appointmentId}
                  onChange={handleChange}
                  required
                  className="
              w-full
              border border-gray-300
              rounded-xl
              px-4 py-3
              bg-white text-slate-800
              outline-none
              focus:ring-2 focus:ring-green-500
              focus:border-green-500
              transition
            "
                >
                  <option value="">Select Appointment</option>

                  {appointments.map((a) => (
                    <option key={a.id} value={a.id}>
                      Appointment #{a.id}
                    </option>
                  ))}
                </select>
              </div>

              {/* Buttons */}
              <div className="flex justify-end gap-3 pt-2">

                <button
                  type="button"
                  onClick={closeModal}
                  className="
              px-5 py-2.5
              rounded-xl
              border border-gray-300
              text-slate-700
              hover:bg-gray-50
              transition
            "
                >
                  Cancel
                </button>

                <button
                  type="submit"
                  className="
              px-5 py-2.5
              rounded-xl
              bg-gradient-to-r from-green-600 to-emerald-600
              text-white
              font-semibold
              hover:from-green-700 hover:to-emerald-700
              shadow-lg
              transition
            "
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