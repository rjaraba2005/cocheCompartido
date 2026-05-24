const API = "";

const logEl = document.getElementById("log");
function log(label, data) {
  const stamp = new Date().toLocaleTimeString();
  const body = typeof data === "string" ? data : JSON.stringify(data, null, 2);
  logEl.textContent = `[${stamp}] ${label}\n${body}\n\n` + logEl.textContent;
}

async function api(path, options = {}) {
  const res = await fetch(API + path, {
    headers: { "Content-Type": "application/json" },
    ...options,
  });
  const text = await res.text();
  const data = text ? JSON.parse(text) : null;
  if (!res.ok) {
    log(`ERROR ${res.status} ${options.method || "GET"} ${path}`, data || text);
    throw new Error(`HTTP ${res.status}`);
  }
  log(`${res.status} ${options.method || "GET"} ${path}`, data);
  return data;
}
//con esto cargamos los usuarios y los vehículos en los desplegables
async function cargarSelects() {
  try {
    const [usuarios, vehiculos] = await Promise.all([
      api("/usuarios"),
      api("/vehiculos"),
    ]);

    const conductorSel = document.querySelector('#form-publicar [name="conductorId"]');
    const pasajeroSel = document.querySelector('#form-reservar [name="pasajeroId"]');
    const vehiculoSel = document.querySelector('#form-publicar [name="vehiculoId"]');

    [conductorSel, pasajeroSel].forEach(sel => {
      sel.innerHTML = usuarios
        .map(u => `<option value="${u.id}">#${u.id} - ${u.email}</option>`)
        .join("");
    });
    vehiculoSel.innerHTML = vehiculos
      .map(v => `<option value="${v.id}">#${v.id} - ${v.marca} ${v.modelo} (${v.color})</option>`)
      .join("");
  } catch (e) {
    console.error(e);
  }
}

// publicaciones de viajes
document.getElementById("form-publicar").addEventListener("submit", async (e) => {
  e.preventDefault();
  const f = e.target;
  const viaje = {
    origen: f.origen.value,
    destino: f.destino.value,
    fecha: f.fecha.value,
    plazas: Number(f.plazas.value),
    precio: Number(f.precio.value),
    mascotas: f.mascotas.checked,
    ninos: f.ninos.checked,
    soloMujeres: f.soloMujeres.checked,
    paradas: f.paradas.checked,
    conductor: { id: Number(f.conductorId.value) },
    vehiculo: { id: Number(f.vehiculoId.value) },
  };
  try {
    await api("/viajes", { method: "POST", body: JSON.stringify(viaje) });
    f.reset();
    listarTodos();
  } catch { }
});

//Buscador de viajes
document.getElementById("form-buscar").addEventListener("submit", async (e) => {
  e.preventDefault();
  const f = e.target;
  const url = `/viajes/buscar?origen=${encodeURIComponent(f.origen.value)}&destino=${encodeURIComponent(f.destino.value)}`;
  try {
    const viajes = await api(url);
    pintarViajes(viajes);
  } catch { }
});

document.getElementById("btn-listar-todos").addEventListener("click", listarTodos);

async function listarTodos() {
  try {
    const viajes = await api("/viajes");
    pintarViajes(viajes);
  } catch { }
}

function pintarViajes(viajes) {
  const tbody = document.querySelector("#tabla-viajes tbody");
  if (!viajes.length) {
    tbody.innerHTML = `<tr><td colspan="8" style="text-align:center;color:#888">Sin resultados</td></tr>`;
    return;
  }
  tbody.innerHTML = viajes.map(v => `
    <tr>
      <td>${v.id}</td>
      <td>${v.origen}</td>
      <td>${v.destino}</td>
      <td>${v.fecha}</td>
      <td>${v.plazas}</td>
      <td>${v.precio} &euro;</td>
      <td>${v.estado}</td>
      <td class="acciones">
        <button class="ok"     data-id="${v.id}" data-action="finalizar">Finalizar</button>
        <button class="danger" data-id="${v.id}" data-action="cancelar">Cancelar</button>
      </td>
    </tr>
  `).join("");
}

document.querySelector("#tabla-viajes tbody").addEventListener("click", async (e) => {
  const btn = e.target.closest("button");
  if (!btn) return;
  const id = btn.dataset.id;
  const action = btn.dataset.action;
  try {
    if (action === "cancelar") {
      await api(`/viajes/${id}`, { method: "DELETE" });
    } else if (action === "finalizar") {
      await api(`/viajes/${id}/estadoFinalizado`, { method: "PUT" });
    }
    listarTodos();
  } catch { }
});

//reserva de viajes
document.getElementById("form-reservar").addEventListener("submit", async (e) => {
  e.preventDefault();
  const f = e.target;
  const reserva = {
    plazas: Number(f.plazas.value),
    pasajero: { id: Number(f.pasajeroId.value) },
  };
  try {
    await api(`/viajes/${f.viajeId.value}/reservas`, {
      method: "POST",
      body: JSON.stringify(reserva),
    });
    listarTodos();
  } catch { }
});

cargarSelects().then(listarTodos);
