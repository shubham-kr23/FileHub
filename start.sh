#!/usr/bin/env bash
set -eu

ROOT_DIR="$(cd "$(dirname "$0")" && pwd)"
RUN_DIR="$ROOT_DIR/run"
BACKEND_DIR="$ROOT_DIR"
FRONTEND_DIR="$ROOT_DIR/frontend/file-storage-ui"

mkdir -p "$RUN_DIR"

print_usage() {
  cat <<EOF
Usage: $0 <start|stop|status|help>

Commands:
  start   Start backend and frontend
  stop    Stop backend and frontend
  status  Show running status
  help    Show this message
EOF
}

start_backend() {
  echo "Starting backend..."
  pushd "$BACKEND_DIR" >/dev/null 2>&1 || return 1
  nohup mvn -Dspring-boot.run.profiles=${SPRING_PROFILES_ACTIVE:-local} spring-boot:run >/dev/null 2>&1 &
  echo $! > "$RUN_DIR/backend.pid"
  echo "Backend PID: $(cat "$RUN_DIR/backend.pid")"
  popd >/dev/null 2>&1
}

start_frontend() {
  echo "Starting frontend..."
  pushd "$FRONTEND_DIR" >/dev/null 2>&1 || return 1
  nohup npm run dev -- --host >/dev/null 2>&1 &
  echo $! > "$RUN_DIR/frontend.pid"
  echo "Frontend PID: $(cat "$RUN_DIR/frontend.pid")"
  popd >/dev/null 2>&1
}

stop_backend() {
  if [ -f "$RUN_DIR/backend.pid" ]; then
    pid=$(cat "$RUN_DIR/backend.pid")
    echo "Stopping backend (PID $pid)..."
    kill "$pid" 2>/dev/null || true
    rm -f "$RUN_DIR/backend.pid"
  else
    echo "Backend not running"
  fi
}

stop_frontend() {
  if [ -f "$RUN_DIR/frontend.pid" ]; then
    pid=$(cat "$RUN_DIR/frontend.pid")
    echo "Stopping frontend (PID $pid)..."
    kill "$pid" 2>/dev/null || true
    rm -f "$RUN_DIR/frontend.pid"
  else
    echo "Frontend not running"
  fi
}

status() {
  if [ -f "$RUN_DIR/backend.pid" ]; then
    pid=$(cat "$RUN_DIR/backend.pid")
    if kill -0 "$pid" >/dev/null 2>&1; then
      echo "Backend running (PID $pid)"
    else
      echo "Backend PID file present but process $pid not running"
    fi
  else
    echo "Backend not running"
  fi

  if [ -f "$RUN_DIR/frontend.pid" ]; then
    pid=$(cat "$RUN_DIR/frontend.pid")
    if kill -0 "$pid" >/dev/null 2>&1; then
      echo "Frontend running (PID $pid)"
    else
      echo "Frontend PID file present but process $pid not running"
    fi
  else
    echo "Frontend not running"
  fi
}

case "${1:-}" in
  start)
    start_backend
    start_frontend
    ;;
  stop)
    stop_frontend
    stop_backend
    ;;
  status)
    status
    ;;
  help|--help|-h|"")
    print_usage
    ;;
  *)
    echo "Unknown command: $1" >&2
    print_usage
    exit 2
    ;;
esac

