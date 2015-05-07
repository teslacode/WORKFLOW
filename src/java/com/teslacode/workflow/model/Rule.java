package com.teslacode.workflow.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Rule Model
 *
 * @author Ade Fruandta
 */
public class Rule {

    private Integer activityId;
    private Map<String, String> data;
    private Map<String, String> result;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public Map<String, String> getResult() {
        return result;
    }

    public void setResult(Map<String, String> result) {
        this.result = result;
    }

    public static Map<String, String> getResult(Map<String, String> data, String rule) {
        Map<String, String> result = new HashMap<>();

        String[] ruleSplit = rule.split("THEN");
        rule = ruleSplit[0].replaceAll("IF ", "").trim();
        String resultTrue = ruleSplit[1].split("ELSE")[0].trim();
        String resultFalse = ruleSplit[1].split("ELSE")[1].trim();

        rule = getConvertVariable(rule.trim(), data);
        rule = getResultOperator(rule);
        String finalResult = getFinalResult(rule);
        result.put("result", finalResult);
        if (finalResult.equalsIgnoreCase("true")) {
            result.put("message", resultTrue);
        } else if (finalResult.equalsIgnoreCase("false")) {
            result.put("message", resultFalse);
        }

        return result;
    }

    //convert variable into data
    private static String getConvertVariable(String rule, Map<String, String> data) {
        String result = "";
        String[] rules = rule.split(" ");
        for (String r : rules) {
            if (r.contains("$")) {
                Object value = data.get(r.replace("$", ""));
                result += value + " ";
            } else {
                result += r + " ";
            }
        }
        return result.trim();
    }
    //convert variable into data

    //convert result rule operator
    private static String getResultOperator(String rule) {
        String result = "";
        String[] rules = rule.trim().split(" ");
        String value1 = "", value2 = "", operator = "";
        for (String r : rules) {
            if (r.equals("=") || r.equals("<") || r.equals("<=") || r.equals(">") || r.equals(">=")) {
                operator = r;
            } else if (r.equals("AND") || r.equals("OR") || r.equals("(") || r.equals(")")) {
                result += r + " ";
            } else {
                if (operator.isEmpty()) {
                    value1 = r;
                } else {
                    value2 = r;
                }
            }
            if (!operator.isEmpty() && !value1.isEmpty() && !value2.isEmpty()) {
                if (operator.equals("=")) {
                    if (value1.equals(value2)) {
                        result += "true ";
                    } else {
                        result += "false ";
                    }
                } else if (operator.equals("<")) {
                    if (Double.valueOf(value1) < Double.valueOf(value2)) {
                        result += "true ";
                    } else {
                        result += "false ";
                    }
                } else if (operator.equals("<=")) {
                    if (Double.valueOf(value1) <= Double.valueOf(value2)) {
                        result += "true ";
                    } else {
                        result += "false ";
                    }
                } else if (operator.equals(">")) {
                    if (Double.valueOf(value1) > Double.valueOf(value2)) {
                        result += "true ";
                    } else {
                        result += "false ";
                    }
                } else if (operator.equals(">=")) {
                    if (Double.valueOf(value1) >= Double.valueOf(value2)) {
                        result += "true ";
                    } else {
                        result += "false ";
                    }
                }
                operator = "";
                value1 = "";
                value2 = "";
            }
        }
        return result.trim();
    }
    //convert result rule operator

    //get final result
    private static String getFinalResult(String rule) {
        String[] rules = rule.trim().split(" ");
        String value1 = "", value2 = "", operator = "";
        for (int i = 0; i < rules.length; i++) {
            String r = rules[i];
            if (r.equals("AND") || r.equals("OR")) {
                operator = r;
            } else if (r.equals("(") || r.equals(")")) {
                if (r.equals("(")) {
                    Boolean find = true;
                    int j = rules.length;
                    while (find) {
                        j--;
                        if (rules[j].equals(")")) {
                            find = false;
                        }
                    }
                    String subRules = "";
                    for (int k = i + 1; k < j; k++) {
                        subRules += rules[k] + " ";
                    }
                    String result = getFinalResult(subRules.trim());
                    if (value1.isEmpty()) {
                        value1 = result;
                    } else {
                        value2 = result;
                    }
                    i = j;
                }
            } else {
                if (operator.isEmpty()) {
                    value1 = r;
                } else {
                    value2 = r;
                }
            }
            if (!operator.isEmpty() && !value1.isEmpty() && !value2.isEmpty()) {
                if (operator.equals("AND")) {
                    if (Boolean.valueOf(value1) && Boolean.valueOf(value2)) {
                        value1 = "true";
                    } else {
                        value1 = "false";
                    }
                } else if (operator.equals("OR")) {
                    if (Boolean.valueOf(value1) || Boolean.valueOf(value2)) {
                        value1 = "true";
                    } else {
                        value1 = "false";
                    }
                }
                operator = "";
                value2 = "";
            }
        }
        return value1.trim();
    }
    //get final result

}
