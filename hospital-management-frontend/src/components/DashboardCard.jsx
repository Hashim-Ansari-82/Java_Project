function DashboardCard({ title, total, growth, icon, color }) {
  return (
    <div
      className={`
        relative overflow-hidden
        rounded-3xl p-5
        bg-gradient-to-br ${color}
        text-white shadow-lg
        transition-all duration-300
        hover:-translate-y-2 hover:shadow-2xl
        cursor-pointer
        min-h-[150px]
        group
      `}
    >

      {/* Background Glow */}
      <div className="absolute -top-10 -right-10 w-32 h-32 bg-white/10 rounded-full blur-3xl group-hover:scale-110 transition-transform duration-500"></div>

      {/* Decorative Ring */}
      <div className="absolute bottom-0 left-0 w-24 h-24 border border-white/10 rounded-full translate-y-10 -translate-x-10"></div>

      {/* Content */}
      <div className="relative z-10 flex flex-col justify-between h-full">

        {/* Top */}
        <div className="flex items-start justify-between gap-4">

          <div className="flex-1 min-w-0">
            <p className="text-xs font-semibold text-white/80 uppercase tracking-[0.2em]">
              {title}
            </p>

            <h2 className="text-4xl font-black mt-3 leading-none tracking-tight">
              {total}
            </h2>
          </div>

          {/* Icon */}
          <div className="
            w-14 h-14 rounded-2xl
            bg-white/20 backdrop-blur-md
            border border-white/20
            flex items-center justify-center
            text-2xl shrink-0
            shadow-lg
            group-hover:rotate-6 group-hover:scale-105
            transition-all duration-300
          ">
            {icon}
          </div>

        </div>

        {/* Bottom */}
        <div className="mt-6 flex items-center justify-between">

          <div className="flex items-center gap-2">
            <span className="w-2.5 h-2.5 rounded-full bg-green-300 animate-pulse"></span>

            <span className="text-sm font-medium text-white/90">
              {growth}
            </span>
          </div>

          <span className="text-xs text-white/70 font-medium">
            Updated now
          </span>
        </div>

      </div>

      {/* Bottom Accent */}
      <div className="absolute bottom-0 left-0 w-full h-1 bg-white/20"></div>

    </div>
  );
}

export default DashboardCard;