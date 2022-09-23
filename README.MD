# Readme

## How To Demo

1. minikube start
2. kubectl apply -f db
3. kubectl apply -f app
4. kubectl apply -f dbreader
5. kubectl logs -f dbreader-......

-> check app operational

6. kubectl get pods -n chaos-testing

-> see 5 chaos-mesh pods running 1/1

7. kubectl get deploy chaos-dashboard -n chaos-testing -o jsonpath="{.spec.template.spec.containers[0].ports[0].containerPort}{'\n'}"
8. kubectl port-forward -n chaos-testing chaos-dashboard-d6bf67bf7-vzzz2 8888:2333
9. localhost:8888
11. Follow token (manager!) instructions
10. Do chaos stuff
- pod kill
- latency to db from app
- pod failure (30s)
- schedule pod kill every minute via * * * * *

## Shutdown

11. kubectl delete deployments app dbreader
12. kubectl delete sts db
13. kubectl delete svc db
14. kubectl delete configmap db-configmap-cnf
15. kubectl delete serviceaccount account-default-manager-.....
16. kubectl delete role role-default-manager-....
17. kubectl delete rolebinding bind-default-manager-......